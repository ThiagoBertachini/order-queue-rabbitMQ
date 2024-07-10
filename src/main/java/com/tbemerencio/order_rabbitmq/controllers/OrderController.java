package com.tbemerencio.order_rabbitmq.controllers;

import com.tbemerencio.order_rabbitmq.dto.ApiResponse;
import com.tbemerencio.order_rabbitmq.dto.OrderResponse;
import com.tbemerencio.order_rabbitmq.dto.PaginationResponse;
import com.tbemerencio.order_rabbitmq.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController @RequestMapping(value = "/api")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrders(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @PathVariable Long customerId){
        var ordersPaged = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOnOrders", totalOnOrders),
                ordersPaged.getContent(),
                PaginationResponse.toPaginationResponse(ordersPaged)
        ));
    }
}
