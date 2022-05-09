package pl.uz.mt.inbox.domain.validation.exception;

import lombok.Getter;

@Getter
public class ValidationErrorException extends RuntimeException {

    private final String type;
    private final String message;

    public ValidationErrorException(final String type, final String message) {
        super(message);
        this.type = type;
        this.message = message;
    }
}
