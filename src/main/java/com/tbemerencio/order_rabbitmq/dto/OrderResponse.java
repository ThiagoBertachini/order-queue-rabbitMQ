package com.tbemerencio.order_rabbitmq.dto;

import com.tbemerencio.order_rabbitmq.entities.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(Long orderId,
                            Long customerId,
                            BigDecimal total) {
    public static OrderResponse toOrderResponse(OrderEntity orderEntity){
        return new OrderResponse(orderEntity.getId(), orderEntity.getCustomerId(), orderEntity.getTotal());
    }
}
