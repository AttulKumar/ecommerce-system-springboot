package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.InvoiceResponse;
import com.ecommerce.ecommerce.model.OrderEntity;
import com.ecommerce.ecommerce.service.OrderService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // PLACE ORDER
    @PostMapping("/place/{userId}")
    public OrderEntity placeOrder(@PathVariable Long userId) {
        return orderService.placeOrder(userId);
    }

    // INVOICE API
    @GetMapping("/invoice/{orderId}")
    public InvoiceResponse getInvoice(@PathVariable Long orderId) {
        return orderService.getInvoice(orderId);
    }

    // ORDER HISTORY
    @GetMapping("/{userId}")
    public List<OrderEntity> getOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}