package pl.uz.mt.inbox.users.application.request;

import pl.uz.mt.inbox.application.exception.ValidationException;
import pl.uz.mt.inbox.domain.validation.AggregatingExceptionHandler;
import pl.uz.mt.inbox.users.application.command.CreateUserCommand;
import pl.uz.mt.inbox.users.application.dto.UserDto;
import pl.uz.mt.inbox.domain.model.UserType;

public record CreateUserRequest(String name, UserType type) {

    public void validate() {
        final var exceptionHandler = new AggregatingExceptionHandler();
        UserDto.validate(name, type, exceptionHandler);
        if (exceptionHandler.hasExceptions()) {
            throw new ValidationException(exceptionHandler.getExceptionErrors());
        }
    }

    public CreateUserCommand toCommand() {
        return new CreateUserCommand(name, type);
    }
}
