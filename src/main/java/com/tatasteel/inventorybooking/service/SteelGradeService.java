package com.tatasteel.inventorybooking.service;

import com.tatasteel.inventorybooking.entity.SteelGrade;
import com.tatasteel.inventorybooking.repository.SteelGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "steelGrades")
    public List<SteelGrade> getAllSteelGrades() {
        System.out.println("Fetching from DATABASE...");
        return steelGradeRepository.findAll();
    }

    @Cacheable(value = "steelGrade", key = "#id")
    public SteelGrade getSteelGradeById(Long id) {
        System.out.println("Fetching from DATABASE for id: " + id);
        return steelGradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Steel grade not found with id: " + id));
    }

    @CacheEvict(value = {"steelGrades", "steelGrade"}, allEntries = true)
    public SteelGrade updateStock(Long id, Double newQuantity) {
        SteelGrade steelGrade = steelGradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Steel grade not found with id: " + id));
        steelGrade.setAvailableQuantity(newQuantity);
        return steelGradeRepository.save(steelGrade);
    }

    @CacheEvict(value = {"steelGrades", "steelGrade"}, allEntries = true)
    public void deleteSteelGrade(Long id) {
        steelGradeRepository.deleteById(id);
    }
}