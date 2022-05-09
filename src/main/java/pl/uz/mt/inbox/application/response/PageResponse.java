package pl.uz.mt.inbox.application.response;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageResponse<T> {

    private final List<T> data;
    private final PaginationResponse pagination;
}

