package pl.uz.mt.inbox.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.uz.mt.inbox.domain.model.UserType;
import pl.uz.mt.inbox.users.application.dto.UserDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private OffsetDateTime creationDate;

    public UserDto toDto() {
        return new UserDto(id, name, type, creationDate);
    }

    public static UserEntity fromDto(final UserDto userDto) {
        return new UserEntity(userDto.id(), userDto.name(), userDto.type(), userDto.creationDate());
    }
}
