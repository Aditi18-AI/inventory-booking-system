package com.tatasteel.inventorybooking.repository;

import com.tatasteel.inventorybooking.entity.SteelGrade;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SteelGradeRepository extends JpaRepository<SteelGrade, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SteelGrade s WHERE s.id = :id")
    Optional<SteelGrade> findByIdWithLock(Long id);
}