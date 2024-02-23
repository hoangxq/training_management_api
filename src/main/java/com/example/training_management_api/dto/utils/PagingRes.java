package com.example.training_management_api.dto.utils;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;

@EqualsAndHashCode(callSuper = true)
@Data
public class PagingRes extends Response {

    private Pagination pagination;

    protected PagingRes(Page<?> page) {
        this.data = page.getContent();
        this.pagination = Pagination.builder()
                .page(page.getNumber() + 1)
                .totalPage(page.getTotalPages())
                .totalRecord(page.getTotalElements())
                .build();
    }

    public static PagingRes of(Page<?> page) {
        return new PagingRes(page);
    }

    @Data
    @Builder
    public static class Pagination {
        private int page;
        private long totalPage;
        private long totalRecord;
    }

}
