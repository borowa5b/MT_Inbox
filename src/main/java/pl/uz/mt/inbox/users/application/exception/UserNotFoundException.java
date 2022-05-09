package pl.uz.mt.inbox.users.application.exception;

import pl.uz.mt.inbox.application.exception.ApplicationException;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(final String id) {
        super(HttpStatus.NOT_FOUND, "inbox:user-not-found", "User with identifier " + id + " not found.");
    }
}
