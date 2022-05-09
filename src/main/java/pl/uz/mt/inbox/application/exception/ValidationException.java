package pl.uz.mt.inbox.application.exception;

import lombok.Getter;
import pl.uz.mt.inbox.domain.validation.exception.ValidationErrorException;

import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ValidationException extends ApplicationException {

    private final List<ValidationErrorException> errors;

    public ValidationException(final List<ValidationErrorException> errors) {
        super(HttpStatus.BAD_REQUEST, "inbox:bad-request", "Parameters didn't passed the validation.");
        this.errors = errors;
    }
}
