package com.tatasteel.inventorybooking.repository;

import com.tatasteel.inventorybooking.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientId(Long clientId);
    List<Order> findBySteelGradeId(Long steelGradeId);
}
