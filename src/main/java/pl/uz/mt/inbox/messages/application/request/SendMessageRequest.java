package pl.uz.mt.inbox.messages.application.request;

import lombok.AllArgsConstructor;
import pl.uz.mt.inbox.application.exception.ValidationException;
import pl.uz.mt.inbox.domain.validation.AggregatingExceptionHandler;
import pl.uz.mt.inbox.domain.validation.Validator;
import pl.uz.mt.inbox.messages.application.command.SendMessageCommand;

@AllArgsConstructor
public class SendMessageRequest {

    private final String content;
    private final String senderId;
    private final String recipientId;

    public SendMessageCommand toCommand() {
        return new SendMessageCommand(content, senderId, recipientId);
    }

    public void validate() {
        final var exceptionHandler = new AggregatingExceptionHandler();

        Validator.isNotNullOrBlank(content, "content", exceptionHandler);
        Validator.isNotNullOrBlank(senderId, "senderId", exceptionHandler);
        Validator.isNotNullOrBlank(recipientId, "recipientId", exceptionHandler);

        if (exceptionHandler.hasExceptions()) {
            throw new ValidationException(exceptionHandler.getExceptionErrors());
        }
    }
}
