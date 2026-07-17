package com.tatasteel.inventorybooking.concurrency;

import com.tatasteel.inventorybooking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ConcurrencySimulator {

    @Autowired
    private OrderService orderService;

    public void simulateConcurrentBookings(Long clientId, Long steelGradeId,
                                           Double quantityPerOrder, int numberOfThreads) {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                try {
                    latch.countDown();
                    latch.await();
                    orderService.placeOrder(clientId, steelGradeId, quantityPerOrder);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                    System.out.println("Booking failed: " + e.getMessage());
                }
            });
        }

        executor.shutdown();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("=== CONCURRENCY TEST RESULTS ===");
        System.out.println("Total threads: " + numberOfThreads);
        System.out.println("Successful bookings: " + successCount.get());
        System.out.println("Failed bookings: " + failCount.get());
        System.out.println("================================");
    }
}
