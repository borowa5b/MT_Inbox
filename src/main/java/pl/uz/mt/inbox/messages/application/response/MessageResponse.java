package pl.uz.mt.inbox.messages.application.response;

import java.time.OffsetDateTime;

public record MessageResponse(String id, String content, MessageUserResponse sender, MessageUserResponse recipient, OffsetDateTime creationDate) {

}
