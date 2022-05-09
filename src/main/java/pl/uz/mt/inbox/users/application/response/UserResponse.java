package pl.uz.mt.inbox.users.application.response;

import pl.uz.mt.inbox.domain.model.UserType;

import java.time.OffsetDateTime;

public record UserResponse(String id, String name, UserType type, OffsetDateTime creationDate) {

}
