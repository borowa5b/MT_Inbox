package pl.uz.mt.inbox.application.response;

public record PaginationResponse(int pageNumber, int pageSize, long totalElements, int totalPages, String sort) {

}
