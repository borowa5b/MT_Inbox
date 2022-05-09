package pl.uz.mt.inbox.users.application.dto;

import pl.uz.mt.inbox.domain.validation.ExceptionHandler;
import pl.uz.mt.inbox.domain.validation.ThrowingExceptionHandler;
import pl.uz.mt.inbox.domain.validation.Validator;
import pl.uz.mt.inbox.domain.model.UserType;

import java.time.OffsetDateTime;

public record UserDto(String id, String name, UserType type, OffsetDateTime creationDate) {

    public UserDto {
        validate(name, type, new ThrowingExceptionHandler());
    }

    public static void validate(final String name, final UserType type, final ExceptionHandler exceptionHandler) {
        Validator.isNotNullOrBlank(name, "name", exceptionHandler);
        Validator.isNotNull(type, "type", exceptionHandler);
    }
}
