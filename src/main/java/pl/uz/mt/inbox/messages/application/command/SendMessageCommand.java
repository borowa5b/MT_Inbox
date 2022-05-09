package pl.uz.mt.inbox.messages.application.command;

public record SendMessageCommand(String content, String senderId, String recipientId) {

}
