package com.tatasteel.inventorybooking.controller;

import com.tatasteel.inventorybooking.concurrency.ConcurrencySimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class ConcurrencyTestController {

    @Autowired
    private ConcurrencySimulator concurrencySimulator;

    @PostMapping("/simulate")
    public ResponseEntity<String> simulate(@RequestParam Long clientId,
                                           @RequestParam Long steelGradeId,
                                           @RequestParam Double quantityPerOrder,
                                           @RequestParam int numberOfThreads) {

        concurrencySimulator.simulateConcurrentBookings(
                clientId, steelGradeId, quantityPerOrder, numberOfThreads);

        return ResponseEntity.ok("Simulation started. Check IntelliJ console for results.");
    }
}
