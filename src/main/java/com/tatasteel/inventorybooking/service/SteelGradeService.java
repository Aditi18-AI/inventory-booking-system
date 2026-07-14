package com.tatasteel.inventorybooking.service;

import com.tatasteel.inventorybooking.entity.SteelGrade;
import com.tatasteel.inventorybooking.repository.SteelGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SteelGradeService {

    @Autowired
    private SteelGradeRepository steelGradeRepository;

    public SteelGrade addSteelGrade(SteelGrade steelGrade) {
        steelGrade.setCreatedAt(LocalDateTime.now());
        return steelGradeRepository.save(steelGrade);
    }

    public List<SteelGrade> getAllSteelGrades() {
        return steelGradeRepository.findAll();
    }

    public SteelGrade getSteelGradeById(Long id) {
        return steelGradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Steel grade not found with id: " + id));
    }

    public SteelGrade updateStock(Long id, Double newQuantity) {
        SteelGrade steelGrade = getSteelGradeById(id);
        steelGrade.setAvailableQuantity(newQuantity);
        return steelGradeRepository.save(steelGrade);
    }

    public void deleteSteelGrade(Long id) {
        steelGradeRepository.deleteById(id);
    }
}