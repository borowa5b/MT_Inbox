package pl.uz.mt.inbox.users.application.endpoint;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.users.application.request.CreateUserRequest;
import pl.uz.mt.inbox.users.application.response.CreateUserResponse;
import pl.uz.mt.inbox.users.application.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CreateUserEndpoint {

    private final UserService service;

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody final CreateUserRequest request) {
        request.validate();
        final var createdUserId = service.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new CreateUserResponse(createdUserId));
    }
}
