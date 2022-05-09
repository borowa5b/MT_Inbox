package pl.uz.mt.inbox.users.application.endpoint;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.users.application.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RemoveUserEndpoint {

    private final UserService service;

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> removeUser(@PathVariable final String id) {
        service.remove(id);
        return ResponseEntity.ok()
                             .build();
    }
}
