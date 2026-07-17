package com.tatasteel.inventorybooking.controller;

import com.tatasteel.inventorybooking.entity.SteelGrade;
import com.tatasteel.inventorybooking.service.SteelGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steel-grades")
public class SteelGradeController {

    @Autowired
    private SteelGradeService steelGradeService;

    @PostMapping
    public ResponseEntity<SteelGrade> addSteelGrade(@RequestBody SteelGrade steelGrade) {
        return ResponseEntity.ok(steelGradeService.addSteelGrade(steelGrade));
    }

    @GetMapping
    public ResponseEntity<List<SteelGrade>> getAllSteelGrades() {
        return ResponseEntity.ok(steelGradeService.getAllSteelGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SteelGrade> getSteelGradeById(@PathVariable Long id) {
        return ResponseEntity.ok(steelGradeService.getSteelGradeById(id));
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<SteelGrade> updateStock(@PathVariable Long id,
                                                  @RequestParam Double quantity) {
        return ResponseEntity.ok(steelGradeService.updateStock(id, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSteelGrade(@PathVariable Long id) {
        steelGradeService.deleteSteelGrade(id);
        return ResponseEntity.ok("Steel grade deleted successfully");
    }
}