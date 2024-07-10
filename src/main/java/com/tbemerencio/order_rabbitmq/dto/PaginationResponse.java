package com.tbemerencio.order_rabbitmq.dto;

import org.springframework.data.domain.Page;

public record PaginationResponse(Integer page,
                                 Integer pageSize,
                                 Integer totalElements,
                                 Integer totalPages) {
    public static PaginationResponse toPaginationResponse(Page<?> page){
        return new PaginationResponse(
                page.getNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.getTotalPages());
    }
}
