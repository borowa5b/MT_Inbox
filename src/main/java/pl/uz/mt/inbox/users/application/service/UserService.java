package pl.uz.mt.inbox.users.application.service;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.application.filter.PageFilter;
import pl.uz.mt.inbox.users.application.command.CreateUserCommand;
import pl.uz.mt.inbox.users.application.dto.UserDto;
import pl.uz.mt.inbox.users.application.exception.UserNotFoundException;
import pl.uz.mt.inbox.users.application.exception.UsernameExistsException;
import pl.uz.mt.inbox.users.domain.repository.UserRepository;
import pl.uz.mt.inbox.users.infrastructure.UserIdGenerator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserIdGenerator idGenerator;

    @Transactional
    public String create(final CreateUserCommand command) {
        final var name = command.name();
        if (userRepository.findByName(name) != null) {
            throw new UsernameExistsException(name);
        }

        final var userDto = new UserDto(idGenerator.generate(), name, command.type(), OffsetDateTime.now());
        return userRepository.save(userDto)
                             .toDto()
                             .id();
    }

    public UserDto findBy(final String id) {
        final var userDto = userRepository.findBy(id);
        if (userDto == null) {
            throw new UserNotFoundException(id);
        }
        return userDto;
    }

    public Page<UserDto> findAll(final PageFilter pageFilter) {
        final var pageable = PageRequest.of(pageFilter.getPageNumber(),
                                            pageFilter.getPageSize(),
                                            Sort.by(pageFilter.getSort()));
        return userRepository.findAll(pageable);
    }

    @Transactional
    public void remove(final String id) {
        if (userRepository.findBy(id) == null) {
            throw new UserNotFoundException(id);
        }
        userRepository.remove(id);
    }
}
