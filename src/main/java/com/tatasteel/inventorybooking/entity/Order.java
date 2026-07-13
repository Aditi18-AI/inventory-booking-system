package com.tatasteel.inventorybooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "steel_grade_id", nullable = false)
    private SteelGrade steelGrade;

    @Column(nullable = false)
    private Double quantityBooked;

    private String status = "PENDING";

    private LocalDateTime createdAt;
}