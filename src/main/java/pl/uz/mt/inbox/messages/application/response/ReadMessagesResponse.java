package pl.uz.mt.inbox.messages.application.response;

import pl.uz.mt.inbox.application.response.PageResponse;
import pl.uz.mt.inbox.application.response.PaginationResponse;

import java.util.List;

public class ReadMessagesResponse extends PageResponse<MessageResponse> {

    public ReadMessagesResponse(final List<MessageResponse> data, final PaginationResponse pagination) {
        super(data, pagination);
    }
}
