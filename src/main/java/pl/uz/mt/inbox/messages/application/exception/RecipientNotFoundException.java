package pl.uz.mt.inbox.messages.application.exception;

import pl.uz.mt.inbox.application.exception.ApplicationException;

import org.springframework.http.HttpStatus;

public class RecipientNotFoundException extends ApplicationException {

    public RecipientNotFoundException(final String id) {
        super(HttpStatus.NOT_FOUND, "inbox:recipient-not-found", "Recipient with identifier " + id + " not found.");
    }
}
