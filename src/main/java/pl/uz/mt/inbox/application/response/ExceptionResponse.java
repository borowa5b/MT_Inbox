package pl.uz.mt.inbox.application.response;

import java.util.List;

public record ExceptionResponse(String type, String message, List<ValidationErrorResponse> errors) {

}
