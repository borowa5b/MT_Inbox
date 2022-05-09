package pl.uz.mt.inbox.users.application.command;

import pl.uz.mt.inbox.domain.model.UserType;

public record CreateUserCommand(String name, UserType type) {

}
