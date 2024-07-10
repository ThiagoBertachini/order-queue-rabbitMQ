package com.tbemerencio.order_rabbitmq.services;

import com.tbemerencio.order_rabbitmq.dto.OrderCreatedEvent;
import com.tbemerencio.order_rabbitmq.dto.OrderResponse;
import com.tbemerencio.order_rabbitmq.repositories.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void saveOrder(OrderCreatedEvent orderCreatedEvent){
        orderRepository.save(orderCreatedEvent.toOrderEntity());
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest){
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponse::toOrderResponse);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId){
        var agregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(agregations, "tb_order", Document.class);
        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }
}