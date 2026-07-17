package com.tatasteel.inventorybooking.controller;

import com.tatasteel.inventorybooking.entity.Order;
import com.tatasteel.inventorybooking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestParam Long clientId,
                                            @RequestParam Long steelGradeId,
                                            @RequestParam Double quantity) {
        return ResponseEntity.ok(orderService.placeOrder(clientId, steelGradeId, quantity));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Order>> getOrdersByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(orderService.getOrdersByClient(clientId));
    }
}