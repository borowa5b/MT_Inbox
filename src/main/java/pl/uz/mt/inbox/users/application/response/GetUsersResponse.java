package pl.uz.mt.inbox.users.application.response;

import pl.uz.mt.inbox.application.response.PageResponse;
import pl.uz.mt.inbox.application.response.PaginationResponse;

import java.util.List;

public class GetUsersResponse extends PageResponse<UserResponse> {

    public GetUsersResponse(final List<UserResponse> data, final PaginationResponse pagination) {
        super(data, pagination);
    }
}
