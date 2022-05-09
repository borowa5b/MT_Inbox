package pl.uz.mt.inbox.users.application.exception;

import pl.uz.mt.inbox.application.exception.ApplicationException;

import org.springframework.http.HttpStatus;

public class UsernameExistsException extends ApplicationException {

    public UsernameExistsException(final String name) {
        super(HttpStatus.CONFLICT, "inbox:user-exists", "Username " + name + " already exists.");
    }
}
