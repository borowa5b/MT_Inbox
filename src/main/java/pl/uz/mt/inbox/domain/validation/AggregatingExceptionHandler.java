package pl.uz.mt.inbox.domain.validation;

import lombok.Getter;
import pl.uz.mt.inbox.domain.validation.exception.ValidationErrorException;

import java.util.ArrayList;
import java.util.List;

public class AggregatingExceptionHandler implements ExceptionHandler {

    @Getter
    private final List<ValidationErrorException> exceptionErrors = new ArrayList<>();

    @Override
    public void add(final ValidationErrorException exception) {
        exceptionErrors.add(exception);
    }

    public boolean hasExceptions() {
        return !exceptionErrors.isEmpty();
    }
}
