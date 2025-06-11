# Concurrency & Multithreading Showcase

This project demonstrates how to build a **production-grade order processing system** using **multithreading** and **concurrency** patterns.

**Features & Techniques**

| Feature | Technology | Description |
|--------|------------|-------------|
| Fire-and-forget tasks | `@Async` | Email confirmation tasks without blocking the main thread |
| Async non-blocking tasks | `CompletableFuture` | Fetching payment, stock, or user data concurrently |
| Thread Pool Execution | `ExecutorService` | Used for CPU-bound background tasks |
| Parallel Task Processing | `ForkJoinPool` | Recursively splits large inventory update lists |
| Thread-Safe Storage | `ConcurrentHashMap` | In-memory order status tracking |
| Atomic Counter | `AtomicInteger` | Safe concurrent order ID generation |
| Producer-Consumer | `BlockingQueue` | Queued background processing of orders |
