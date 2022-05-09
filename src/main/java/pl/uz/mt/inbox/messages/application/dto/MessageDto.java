package pl.uz.mt.inbox.messages.application.dto;

import pl.uz.mt.inbox.domain.validation.ExceptionHandler;
import pl.uz.mt.inbox.domain.validation.ThrowingExceptionHandler;
import pl.uz.mt.inbox.domain.validation.Validator;

import java.time.OffsetDateTime;

public record MessageDto(String id, String content, MessageUserDto sender, MessageUserDto recipient,
                         OffsetDateTime creationDate) {

    public MessageDto {
        validate(content, sender, recipient, new ThrowingExceptionHandler());
    }

    public static void validate(final String content,
                                final MessageUserDto sender,
                                final MessageUserDto recipient,
                                final ExceptionHandler exceptionHandler) {
        Validator.isNotNullOrBlank(content, "content", exceptionHandler);
        Validator.isNotNull(content, "sender", exceptionHandler);
        Validator.isNotNull(content, "recipient", exceptionHandler);
    }
}
