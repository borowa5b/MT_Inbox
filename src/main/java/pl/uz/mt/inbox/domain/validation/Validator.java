package pl.uz.mt.inbox.domain.validation;

import pl.uz.mt.inbox.domain.validation.exception.ValidationErrorException;
import pl.uz.mt.inbox.domain.validation.exception.ValidationExceptionErrorType;

public class Validator {

    public static void isNotNull(final Object field, final String fieldName, final ExceptionHandler exceptionHandler) {
        if (field == null) {
            final var type = ValidationExceptionErrorType.NOT_NULL.getType();
            final var message = ValidationExceptionErrorType.NOT_NULL.getMessage();
            exceptionHandler.add(new ValidationErrorException(type, message.replace("{0}", fieldName)));
        }
    }

    public static void isNotNullOrBlank(final String field,
                                        final String fieldName,
                                        final ExceptionHandler exceptionHandler) {
        if (field == null || field.isBlank()) {
            final var type = ValidationExceptionErrorType.NOT_NULL_OR_BLANK.getType();
            final var message = ValidationExceptionErrorType.NOT_NULL_OR_BLANK.getMessage();
            exceptionHandler.add(new ValidationErrorException(type, message.replace("{0}", fieldName)));
        }
    }

    public static void isGreaterThan(final int field,
                                     final String fieldName,
                                     final int greaterThan,
                                     final ExceptionHandler exceptionHandler) {
        if (field <= greaterThan) {
            final var type = ValidationExceptionErrorType.GREATER_THAN.getType();
            final var message = ValidationExceptionErrorType.GREATER_THAN.getMessage();
            exceptionHandler.add(new ValidationErrorException(type,
                                                              message.replace("{0}", fieldName)
                                                                     .replace("{1}", String.valueOf(greaterThan))));
        }
    }

    public static void isGreaterOrEqualThan(final int field,
                                            final String fieldName,
                                            final int greaterOrEqualThan,
                                            final ExceptionHandler exceptionHandler) {
        if (field < greaterOrEqualThan) {
            final var type = ValidationExceptionErrorType.GREATER_OR_EQUAL_THAN.getType();
            final var message = ValidationExceptionErrorType.GREATER_OR_EQUAL_THAN.getMessage();
            exceptionHandler.add(new ValidationErrorException(type,
                                                              message.replace("{0}", fieldName)
                                                                     .replace("{1}",
                                                                              String.valueOf(greaterOrEqualThan))));
        }
    }
}
