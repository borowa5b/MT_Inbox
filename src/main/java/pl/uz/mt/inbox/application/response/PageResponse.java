package pl.uz.mt.inbox.application.response;

import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PageResponse<T> {

    private final List<T> data;
    private final PaginationResponse pagination;
}

