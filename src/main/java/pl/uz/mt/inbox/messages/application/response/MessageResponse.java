package pl.uz.mt.inbox.messages.application.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessageResponse {

    private final String id;
    private final String content;
    private final MessageUserResponse sender;
    private final MessageUserResponse recipient;
}
