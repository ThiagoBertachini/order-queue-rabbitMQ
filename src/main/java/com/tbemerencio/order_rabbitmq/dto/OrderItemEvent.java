package com.tbemerencio.order_rabbitmq.dto;

import com.tbemerencio.order_rabbitmq.entities.OrderItem;

import java.math.BigDecimal;

public record OrderItemEvent(String produto,
                             Integer quantidade,
                             BigDecimal preco) {
    public OrderItem toOrderItem(){
        return new OrderItem(produto, quantidade, preco);
    }
}
