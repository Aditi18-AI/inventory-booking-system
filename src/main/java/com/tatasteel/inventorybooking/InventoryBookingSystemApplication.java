package com.tatasteel.inventorybooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InventoryBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryBookingSystemApplication.class, args);
    }
}

