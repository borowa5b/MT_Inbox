package pl.uz.mt.inbox.application.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String type;
    private final String message;

    public ApplicationException(final HttpStatus httpStatus, final String type, final String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.type = type;
        this.message = message;
    }
}
