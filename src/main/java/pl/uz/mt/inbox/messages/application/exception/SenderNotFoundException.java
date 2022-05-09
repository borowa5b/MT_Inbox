package pl.uz.mt.inbox.messages.application.exception;

import pl.uz.mt.inbox.application.exception.ApplicationException;

import org.springframework.http.HttpStatus;

public class SenderNotFoundException extends ApplicationException {

    public SenderNotFoundException(final String id) {
        super(HttpStatus.NOT_FOUND, "inbox:sender-not-found", "Sender with identifier " + id + " not found.");
    }
}
