package pl.uz.mt.inbox.messages.application.exception;

import pl.uz.mt.inbox.application.exception.ApplicationException;

import org.springframework.http.HttpStatus;

public class MessageNotFoundException extends ApplicationException {

    public MessageNotFoundException(final String id) {
        super(HttpStatus.NOT_FOUND, "inbox:message-not-found", "Message with identifier " + id + " not found.");
    }
}
