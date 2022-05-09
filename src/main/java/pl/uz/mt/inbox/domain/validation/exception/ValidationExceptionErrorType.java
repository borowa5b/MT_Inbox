package pl.uz.mt.inbox.domain.validation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationExceptionErrorType {

    NOT_NULL("not-null", "Field '{0}' can't be null."),

    NOT_NULL_OR_BLANK("not-null-or-blank", "Field '{0}' can't be null or blank."),

    GREATER_THAN("less-than", "Field '{0}' should have value greater than {1}."),

    GREATER_OR_EQUAL_THAN("less-than", "Field '{0}' should have value greater or equal to {1}.");

    private final String type;
    private final String message;
}
