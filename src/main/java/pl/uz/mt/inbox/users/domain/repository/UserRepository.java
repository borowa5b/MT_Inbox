package pl.uz.mt.inbox.users.domain.repository;

import pl.uz.mt.inbox.users.application.dto.UserDto;
import pl.uz.mt.inbox.users.domain.model.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {

    UserDto findBy(final String id);

    UserDto findByName(final String name);

    Page<UserDto> findAll(final Pageable pageable);

    UserEntity save(final UserDto userDto);
    void remove(final String id);
}
