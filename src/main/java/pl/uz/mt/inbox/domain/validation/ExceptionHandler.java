package pl.uz.mt.inbox.domain.validation;

import pl.uz.mt.inbox.domain.validation.exception.ValidationErrorException;

public interface ExceptionHandler {

    void add(final ValidationErrorException exception);
}
