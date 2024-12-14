package com.project.pizza.service;

import com.project.pizza.persistence.entity.OrderEntity;
import com.project.pizza.persistence.projection.OrderSummary;
import com.project.pizza.persistence.repository.OrderRepository;
import com.project.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private static final String DELIVERY = "D";
    private static final String CARRY_OUT = "C";
    private static final String ON_SITE = "S";

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDateTime.now().toLocalDate().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        // D --> Delivery
        // C --> Carry out
        List<String> methods = Arrays.asList(DELIVERY, CARRY_OUT);

        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String customerId) {
        return this.orderRepository.findCustomerOrders(customerId);
    }

    public OrderSummary getOrderSummary(int orderId) {
        return this.orderRepository.getOrderSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }

}
