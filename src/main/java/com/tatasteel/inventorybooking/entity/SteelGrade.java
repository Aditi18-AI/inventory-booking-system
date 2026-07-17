package com.tatasteel.inventorybooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "steel_grades")
public class SteelGrade implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gradeName;

    private String description;

    @Column(nullable = false)
    private Double availableQuantity;

    private String unit = "tonnes";

    private LocalDateTime createdAt;
}