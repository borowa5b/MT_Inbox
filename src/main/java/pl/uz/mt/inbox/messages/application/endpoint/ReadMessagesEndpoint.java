package pl.uz.mt.inbox.messages.application.endpoint;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.application.factory.PaginationFactory;
import pl.uz.mt.inbox.application.filter.PageFilter;
import pl.uz.mt.inbox.messages.application.dto.MessageDto;
import pl.uz.mt.inbox.messages.application.response.MessageResponse;
import pl.uz.mt.inbox.messages.application.response.MessageUserResponse;
import pl.uz.mt.inbox.messages.application.response.ReadMessagesResponse;
import pl.uz.mt.inbox.messages.application.service.MessageService;

import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ReadMessagesEndpoint {

    private final MessageService service;

    @GetMapping(value = "/messages",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReadMessagesResponse> readMessage(final PageFilter pageFilter) {
        pageFilter.setSort(Sort.Order.desc("creationDate"));
        final var messages = service.readAll(pageFilter);
        final var data = messages.getContent()
                                 .stream()
                                 .map(toReadMessageResponse())
                                 .collect(Collectors.toList());
        final var body = new ReadMessagesResponse(data, PaginationFactory.create(messages));
        return ResponseEntity.ok(body);
    }

    private Function<MessageDto, MessageResponse> toReadMessageResponse() {
        return messageDto -> {
            final var senderResponse = new MessageUserResponse(messageDto.sender()
                                                                         .name());
            final var recipientResponse = new MessageUserResponse(messageDto.recipient()
                                                                            .name());
            return new MessageResponse(messageDto.id(), messageDto.content(), senderResponse, recipientResponse);
        };
    }
}
