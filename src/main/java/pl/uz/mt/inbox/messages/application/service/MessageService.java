package pl.uz.mt.inbox.messages.application.service;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.application.filter.PageFilter;
import pl.uz.mt.inbox.messages.application.command.SendMessageCommand;
import pl.uz.mt.inbox.messages.application.dto.MessageDto;
import pl.uz.mt.inbox.messages.application.exception.MessageNotFoundException;
import pl.uz.mt.inbox.messages.application.exception.RecipientNotFoundException;
import pl.uz.mt.inbox.messages.application.exception.SenderNotFoundException;
import pl.uz.mt.inbox.messages.domain.repository.MessageRepository;
import pl.uz.mt.inbox.messages.domain.repository.MessageUserRepository;
import pl.uz.mt.inbox.messages.infrastructure.MessageIdGenerator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageUserRepository messageUserRepository;
    private final MessageIdGenerator idGenerator;

    public MessageDto readBy(final String id) {
        final var messageDto = messageRepository.findBy(id);
        if (messageDto == null) {
            throw new MessageNotFoundException(id);
        }
        return messageDto;
    }

    public Page<MessageDto> readAll(final PageFilter pageFilter) {
        final var pageable = PageRequest.of(pageFilter.getPageNumber(),
                                            pageFilter.getPageSize(),
                                            Sort.by(pageFilter.getSort()));
        return messageRepository.findAll(pageable);
    }

    @Transactional
    public String send(final SendMessageCommand command) {
        final var senderId = command.senderId();
        final var recipientId = command.recipientId();
        final var sender = messageUserRepository.findSenderBy(senderId);
        final var recipient = messageUserRepository.findRecipientBy(recipientId);
        if (sender == null) {
            throw new SenderNotFoundException(senderId);
        }
        if (recipient == null) {
            throw new RecipientNotFoundException(recipientId);
        }

        final var messageDto = new MessageDto(idGenerator.generate(),
                                              command.content(),
                                              sender,
                                              recipient,
                                              OffsetDateTime.now());
        return messageRepository.save(messageDto)
                                .toDto()
                                .id();
    }
}
