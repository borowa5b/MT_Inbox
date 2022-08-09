package pl.uz.mt.inbox.messages.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.uz.mt.inbox.domain.model.UserType;
import pl.uz.mt.inbox.messages.application.dto.MessageUserDto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inbox_user")
public class MessageUserEntity {

    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private UserType type;

    public MessageUserDto toDto() {
        return new MessageUserDto(id, name, type);
    }

    public static MessageUserEntity fromDto(final MessageUserDto messageUserDto) {
        return new MessageUserEntity(messageUserDto.id(), messageUserDto.name(), messageUserDto.type());
    }
}
