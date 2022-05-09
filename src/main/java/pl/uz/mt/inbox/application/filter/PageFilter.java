package pl.uz.mt.inbox.application.filter;

import lombok.Getter;
import lombok.Setter;
import pl.uz.mt.inbox.application.exception.ValidationException;
import pl.uz.mt.inbox.domain.validation.AggregatingExceptionHandler;
import pl.uz.mt.inbox.domain.validation.Validator;

import org.springframework.data.domain.Sort;

@Getter
public class PageFilter {

    private Integer pageNumber;
    private Integer pageSize;

    @Setter
    private Sort.Order sort;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 50;

    public PageFilter(final Integer pageNumber, final Integer pageSize, final Sort.Order sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;

        if (this.pageNumber == null) {
            this.pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (this.pageSize == null) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        validate();
    }

    private void validate() {
        final var exceptionHandler = new AggregatingExceptionHandler();

        Validator.isGreaterOrEqualThan(pageNumber, "pageNumber", 0, exceptionHandler);
        Validator.isGreaterThan(pageSize, "pageSize", 0, exceptionHandler);

        if (exceptionHandler.hasExceptions()) {
            throw new ValidationException(exceptionHandler.getExceptionErrors());
        }
    }
}
