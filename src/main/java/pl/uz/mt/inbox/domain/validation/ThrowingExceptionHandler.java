package pl.uz.mt.inbox.domain.validation;

import pl.uz.mt.inbox.domain.validation.exception.ValidationErrorException;

public class ThrowingExceptionHandler implements ExceptionHandler {

    @Override
    public void add(final ValidationErrorException exception) {
        throw exception;
    }
}
