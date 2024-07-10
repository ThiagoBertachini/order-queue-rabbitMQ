package com.tbemerencio.order_rabbitmq.dto;

import java.util.List;
import java.util.Map;

public record ApiResponse<T>(Map<String, Object> summary,
                             List<T> data, PaginationResponse paginationResponse) {
}
