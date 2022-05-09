package pl.uz.mt.inbox.messages.domain.repository;

import pl.uz.mt.inbox.messages.application.dto.MessageDto;
import pl.uz.mt.inbox.messages.domain.model.MessageEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRepository {

    MessageDto findBy(final String id);

    Page<MessageDto> findAll(final Pageable pageable);

    MessageEntity save(final MessageDto messageDto);
}
