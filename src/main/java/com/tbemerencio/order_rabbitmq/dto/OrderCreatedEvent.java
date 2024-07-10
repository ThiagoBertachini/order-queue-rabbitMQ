package com.tbemerencio.order_rabbitmq.dto;

import com.tbemerencio.order_rabbitmq.entities.OrderEntity;
import com.tbemerencio.order_rabbitmq.entities.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record OrderCreatedEvent(Long codigoPedido,
                                Long codigoCliente,
                                List<OrderItemEvent> items) {

    public OrderEntity toOrderEntity(){
        return new OrderEntity(
                codigoPedido,
                codigoCliente,
                calculateTotal(items),
                toOrderItemEvent(items));
    }

    private List<OrderItem> toOrderItemEvent(List<OrderItemEvent> items) {

        return items.stream().map(OrderItemEvent::toOrderItem).collect(Collectors.toList());
    }

    private BigDecimal calculateTotal(List<OrderItemEvent> items) {
        var total = BigDecimal.ZERO;
        for (OrderItemEvent item : items) {
            total = total.add(item.preco());
            total = BigDecimal.valueOf(item.quantidade()).multiply(total);
        }
        return total;
    }
}