package pl.uz.mt.inbox.messages.application.endpoint;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.messages.application.response.MessageResponse;
import pl.uz.mt.inbox.messages.application.response.MessageUserResponse;
import pl.uz.mt.inbox.messages.application.service.MessageService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReadMessageEndpoint {

    private final MessageService service;

    @GetMapping("/messages/{id}")
    public MessageResponse readMessage(@PathVariable final String id) {
        final var messageDto = service.readBy(id);
        final var sender = messageDto.sender();
        final var senderResponse = new MessageUserResponse(sender.id(), sender.name());
        final var recipient = messageDto.recipient();
        final var recipientResponse = new MessageUserResponse(recipient.id(), recipient.name());
        return new MessageResponse(messageDto.id(),
                                   messageDto.content(),
                                   senderResponse,
                                   recipientResponse,
                                   messageDto.creationDate());
    }
}
