package pl.uz.mt.inbox.users.infrastructure.repository;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.users.application.dto.UserDto;
import pl.uz.mt.inbox.users.domain.model.UserEntity;
import pl.uz.mt.inbox.users.domain.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SpringUserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByName(final String name);
}

@Component
@AllArgsConstructor
public class DefaultUserRepository implements UserRepository {

    private final SpringUserRepository repository;

    @Override
    public UserDto findBy(final String id) {
        return repository.findById(id)
                         .map(UserEntity::toDto)
                         .orElse(null);
    }

    @Override
    public UserDto findByName(final String name) {
        return repository.findByName(name)
                         .map(UserEntity::toDto)
                         .orElse(null);
    }

    @Override
    public Page<UserDto> findAll(final Pageable pageable) {
        return repository.findAll(pageable)
                         .map(UserEntity::toDto);
    }

    @Override
    public UserEntity save(final UserDto userDto) {
        return repository.save(UserEntity.fromDto(userDto));
    }

    @Override
    public void remove(final String id) {
        repository.deleteById(id);
    }
}
