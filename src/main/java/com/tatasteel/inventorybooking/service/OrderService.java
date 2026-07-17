package com.tatasteel.inventorybooking.service;

import com.tatasteel.inventorybooking.entity.Order;
import com.tatasteel.inventorybooking.entity.SteelGrade;
import com.tatasteel.inventorybooking.repository.OrderRepository;
import com.tatasteel.inventorybooking.repository.SteelGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SteelGradeRepository steelGradeRepository;

    @Autowired
    private ClientService clientService;

    @Transactional
    public Order placeOrder(Long clientId, Long steelGradeId, Double quantity) {

        SteelGrade steelGrade = steelGradeRepository.findByIdWithLock(steelGradeId)
                .orElseThrow(() -> new RuntimeException("Steel grade not found"));

        if (steelGrade.getAvailableQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock. Available: "
                    + steelGrade.getAvailableQuantity() + " tonnes");
        }

        steelGrade.setAvailableQuantity(steelGrade.getAvailableQuantity() - quantity);
        steelGradeRepository.save(steelGrade);

        Order order = new Order();
        order.setClient(clientService.getClientById(clientId));
        order.setSteelGrade(steelGrade);
        order.setQuantityBooked(quantity);
        order.setStatus("CONFIRMED");
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByClient(Long clientId) {
        return orderRepository.findByClientId(clientId);
    }
}