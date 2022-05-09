package pl.uz.mt.inbox.users.infrastructure;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.domain.IdGenerator;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserIdGenerator {

    private final IdGenerator idGenerator;

    public String generate() {
        return idGenerator.generate("US");
    }
}
