package pl.uz.mt.inbox.application.exception.handler;

import pl.uz.mt.inbox.application.exception.ApplicationException;
import pl.uz.mt.inbox.application.exception.ValidationException;
import pl.uz.mt.inbox.application.response.ExceptionResponse;
import pl.uz.mt.inbox.application.response.ValidationErrorResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<Object> handle(final RuntimeException exception, final WebRequest request) {
        if (exception.getCause() instanceof final ValidationException validationException) {
            return handle(validationException, request);
        }

        final var body = new ExceptionResponse("inbox:unexpected-error", exception.getMessage(), null);
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value = { ApplicationException.class })
    public ResponseEntity<Object> handle(final ApplicationException exception, final WebRequest request) {
        final var body = new ExceptionResponse(exception.getType(), exception.getMessage(), null);
        return handleExceptionInternal(exception, body, new HttpHeaders(), exception.getHttpStatus(), request);
    }

    @ExceptionHandler(value = { ValidationException.class })
    public ResponseEntity<Object> handle(final ValidationException exception, final WebRequest request) {
        final var body = new ExceptionResponse(exception.getType(),
                                               exception.getMessage(),
                                               exception.getErrors()
                                                        .stream()
                                                        .map(error -> new ValidationErrorResponse(error.getType(),
                                                                                                  error.getMessage()))
                                                        .collect(Collectors.toList()));
        return handleExceptionInternal(exception, body, new HttpHeaders(), exception.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException exception,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final var body = new ExceptionResponse("inbox:bad-request", exception.getMessage(), null);
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
