package pl.uz.mt.inbox.messages.application.dto;

import pl.uz.mt.inbox.domain.model.UserType;

public record MessageUserDto(String id, String name, UserType type) {

}
