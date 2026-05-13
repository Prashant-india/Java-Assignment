package com.shop;

public class Task3 {
    // FIX: Use AtomicInteger to ensure thread-safe increments (atomic operation)
    private AtomicInteger processedCount = new AtomicInteger(0);

    public void process(List<StatementRecord> records) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (StatementRecord record : records) {
            executor.submit(() -> {
                processRecord(record);
                // FIX: incrementAndGet() prevents race conditions where threads overwrite each other
                processedCount.incrementAndGet();
            });
        }
        executor.shutdown();

            executor.awaitTermination(5, TimeUnit.MINUTES);

    }

    public int getProcessedCount() {
        return processedCount.get();
    }

}
