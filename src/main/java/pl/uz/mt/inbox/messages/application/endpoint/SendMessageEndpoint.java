package pl.uz.mt.inbox.messages.application.endpoint;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.messages.application.request.SendMessageRequest;
import pl.uz.mt.inbox.messages.application.response.SendMessageResponse;
import pl.uz.mt.inbox.messages.application.service.MessageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SendMessageEndpoint {

    private final MessageService service;

    @PutMapping(value = "/messages",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestBody final SendMessageRequest request) {
        request.validate();
        final var body = new SendMessageResponse(service.send(request.toCommand()));
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(body);
    }
}
