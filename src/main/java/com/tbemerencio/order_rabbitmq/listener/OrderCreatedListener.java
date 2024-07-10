package com.tbemerencio.order_rabbitmq.listener;

import com.tbemerencio.order_rabbitmq.dto.OrderCreatedEvent;
import com.tbemerencio.order_rabbitmq.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.tbemerencio.order_rabbitmq.configs.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listener(Message<OrderCreatedEvent> message){
        logger.info("message consumed, {}", message);
        orderService.saveOrder(message.getPayload());
    }
}