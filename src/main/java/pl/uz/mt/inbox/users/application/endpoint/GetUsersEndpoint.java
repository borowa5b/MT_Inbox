package pl.uz.mt.inbox.users.application.endpoint;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.application.factory.PaginationFactory;
import pl.uz.mt.inbox.application.filter.PageFilter;
import pl.uz.mt.inbox.users.application.response.GetUsersResponse;
import pl.uz.mt.inbox.users.application.response.UserResponse;
import pl.uz.mt.inbox.users.application.service.UserService;

import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class GetUsersEndpoint {

    private final UserService service;

    @GetMapping(value = "/users",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUsersResponse> getUsers(final PageFilter pageFilter) {
        pageFilter.setSort(Sort.Order.desc("creationDate"));
        final var users = service.findAll(pageFilter);
        final var body = new GetUsersResponse(users.getContent()
                                                   .stream()
                                                   .map(userDto -> new UserResponse(userDto.id(),
                                                                                    userDto.name(),
                                                                                    userDto.type(),
                                                                                    userDto.creationDate()))
                                                   .collect(Collectors.toList()), PaginationFactory.create(users));
        return ResponseEntity.ok(body);
    }
}
