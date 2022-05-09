package pl.uz.mt.inbox.users.application.response;

import pl.uz.mt.inbox.application.response.PageResponse;
import pl.uz.mt.inbox.application.response.PaginationResponse;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetUsersResponse extends PageResponse<UserResponse> {

    public GetUsersResponse(final List<UserResponse> data, final PaginationResponse pagination) {
        super(data, pagination);
    }
}
