package com.tatasteel.inventorybooking.repository;

import com.tatasteel.inventorybooking.entity.SteelGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteelGradeRepository extends JpaRepository<SteelGrade, Long> {
}