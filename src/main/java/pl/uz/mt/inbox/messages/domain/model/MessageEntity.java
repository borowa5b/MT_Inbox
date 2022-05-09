package pl.uz.mt.inbox.messages.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.uz.mt.inbox.messages.application.dto.MessageDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MESSAGE")
public class MessageEntity {

    @Id
    private String id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private MessageUserEntity sender;

    @ManyToOne
    @JoinColumn(name = "RECIPIENT_ID")
    private MessageUserEntity recipient;

    private OffsetDateTime creationDate;

    public MessageDto toDto() {
        return new MessageDto(id, content, sender.toDto(), recipient.toDto(), creationDate);
    }

    public static MessageEntity fromDto(final MessageDto messageDto) {
        return new MessageEntity(messageDto.id(),
                                 messageDto.content(),
                                 MessageUserEntity.fromDto(messageDto.sender()),
                                 MessageUserEntity.fromDto(messageDto.recipient()),
                                 messageDto.creationDate());
    }
}
