package pl.uz.mt.inbox.application.factory;

import pl.uz.mt.inbox.application.response.PaginationResponse;

import org.springframework.data.domain.Page;

public class PaginationFactory {

    public static PaginationResponse create(final Page<?> page) {
        return new PaginationResponse(page.getNumber(),
                                      page.getSize(),
                                      page.getTotalElements(),
                                      page.getTotalPages(),
                                      page.getSort()
                                          .toString());
    }
}
