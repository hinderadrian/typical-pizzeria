package com.project.pizza.web.controller;

import com.project.pizza.persistence.entity.OrderEntity;
import com.project.pizza.persistence.projection.OrderSummary;
import com.project.pizza.service.OrderService;
import com.project.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String customerId) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(customerId));
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int orderId) {
        return ResponseEntity.ok(this.orderService.getOrderSummary(orderId));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> saveRandomOrder(@RequestBody RandomOrderDto randomOrderDto) {
        return ResponseEntity.ok(this.orderService.saveRandomOrder(randomOrderDto));
    }
}
