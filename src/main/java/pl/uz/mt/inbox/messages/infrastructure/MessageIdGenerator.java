package pl.uz.mt.inbox.messages.infrastructure;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.domain.IdGenerator;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageIdGenerator {

    private final IdGenerator idGenerator;

    public String generate() {
        return idGenerator.generate("MS");
    }
}
