package pl.uz.mt.inbox.users.application.endpoint;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.users.application.response.UserResponse;
import pl.uz.mt.inbox.users.application.service.UserService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GetUserEndpoint {

    private final UserService service;

    @GetMapping(value = "/users/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUser(@PathVariable final String id) {
        final var userDto = service.findBy(id);
        final var body = new UserResponse(userDto.id(), userDto.name(), userDto.type(), userDto.creationDate());
        return ResponseEntity.ok(body);
    }
}
