package pl.uz.mt.inbox.messages.infrastructure.repository;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.domain.model.UserType;
import pl.uz.mt.inbox.messages.application.dto.MessageUserDto;
import pl.uz.mt.inbox.messages.domain.model.MessageUserEntity;
import pl.uz.mt.inbox.messages.domain.repository.MessageUserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SpringMessageUserRepository extends JpaRepository<MessageUserEntity, String> {

    @Query("SELECT mu FROM MessageUserEntity mu WHERE mu.type = :type AND mu.id = :id")
    Optional<MessageUserEntity> findBy(final UserType type, final String id);
}

@Component
@AllArgsConstructor
public class DefaultMessageUserRepository implements MessageUserRepository {

    private final SpringMessageUserRepository repository;

    @Override
    public MessageUserDto findSenderBy(final String id) {
        return repository.findBy(UserType.SENDER, id)
                         .map(MessageUserEntity::toDto)
                         .orElse(null);
    }

    @Override
    public MessageUserDto findRecipientBy(final String id) {
        return repository.findBy(UserType.RECIPIENT, id)
                         .map(MessageUserEntity::toDto)
                         .orElse(null);
    }
}
