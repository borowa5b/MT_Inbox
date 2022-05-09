package pl.uz.mt.inbox.messages.domain.repository;

import pl.uz.mt.inbox.messages.application.dto.MessageUserDto;

public interface MessageUserRepository {

    MessageUserDto findSenderBy(final String id);

    MessageUserDto findRecipientBy(final String id);
}
