package pl.uz.mt.inbox.messages.infrastructure.repository;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.messages.application.dto.MessageDto;
import pl.uz.mt.inbox.messages.domain.model.MessageEntity;
import pl.uz.mt.inbox.messages.domain.repository.MessageRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
interface SpringMessageRepository extends JpaRepository<MessageEntity, String> {

}

@Component
@AllArgsConstructor
public class DefaultMessageRepository implements MessageRepository {

    private final SpringMessageRepository repository;

    @Override
    public MessageDto findBy(final String id) {
        return repository.findById(id)
                         .map(MessageEntity::toDto)
                         .orElse(null);
    }

    @Override
    public Page<MessageDto> findAll(final Pageable pageable) {
        return repository.findAll(pageable)
                         .map(MessageEntity::toDto);
    }

    @Override
    public MessageEntity save(final MessageDto messageDto) {
        return repository.save(MessageEntity.fromDto(messageDto));
    }
}
