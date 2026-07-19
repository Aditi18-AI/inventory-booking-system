# Inventory Booking System

A high-concurrency B2B inventory booking system built with Java and Spring Boot, inspired by Amazon's cart and inventory architecture. Designed to handle simultaneous client booking requests for steel coils without double-booking or stock overselling.

## Business Context
Tata Steel deals with B2B clients ordering massive batches of steel coils. Multiple global clients may attempt to book the same steel grade at the exact same millisecond. This system ensures accurate inventory allocation under peak concurrent load.

## Tech Stack
- **Java 21** — Core language
- **Spring Boot 3** — Backend framework
- **MySQL 8** — Primary database
- **Redis 7** — Caching layer for real-time stock counts
- **Docker + Docker Compose** — Containerization
- **Gradle** — Build tool
- **Postman** — API testing

## Key Features
- REST APIs for steel grade management, client management, and order booking
- Pessimistic locking to prevent race conditions under concurrent load
- Redis caching with TTL-based expiry and cache eviction on stock updates
- ACID-compliant transactions ensuring no double booking
- Concurrency simulation — 20 simultaneous threads tested, 0 overselling
- Fully containerized with Docker Compose (app + MySQL + Redis)

## Architecture