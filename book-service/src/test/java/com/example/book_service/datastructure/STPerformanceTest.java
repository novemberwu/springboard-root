package com.example.book_service.datastructure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class STPerformanceTest {

    private final int[] DATASET_SIZES = {10000, 100000, 200000, 400000};
    private final int GET_OPERATIONS = 5000;
    private final int WARMUP_ITERATIONS = 100;
    private volatile int resultHolder; // Use a volatile field to prevent dead code elimination

    @Test
    public void testGetPerformance() {
        System.out.println("Running performance tests for SortedST vs. UnsortedST...");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-15s | %-25s | %-25s\n", "Dataset Size", "Avg. Time UnsortedST (ns)", "Avg. Time SortedST (ns)");
        System.out.println("------------------------------------------------------------------");

        for (int size : DATASET_SIZES) {
            // Prepare data
            List<Integer> keys = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                keys.add(i);
            }
            Collections.shuffle(keys);

            // Populate STs
            ST<Integer, Integer> unsortedST = new UnsortedST<>(size);
            ST<Integer, Integer> sortedST = new SortedST<>(size);
            for (Integer key : keys) {
                unsortedST.put(key, key);
                sortedST.put(key, key);
            }

            // --- Warm-up Phase ---
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                Integer keyToGet = keys.get(i % size);
                resultHolder += unsortedST.get(keyToGet);
                resultHolder += sortedST.get(keyToGet);
            }

            // --- Test UnsortedST ---
            long totalUnsortedTime = 0;
            for (int i = 0; i < GET_OPERATIONS; i++) {
                Integer keyToGet = keys.get(i % size);
                long startTime = System.nanoTime();
                resultHolder += unsortedST.get(keyToGet);
                long endTime = System.nanoTime();
                totalUnsortedTime += (endTime - startTime);
            }
            long avgUnsortedTime = totalUnsortedTime / GET_OPERATIONS;

            // --- Test SortedST ---
            long totalSortedTime = 0;
            for (int i = 0; i < GET_OPERATIONS; i++) {
                Integer keyToGet = keys.get(i % size);
                long startTime = System.nanoTime();
                resultHolder += sortedST.get(keyToGet);
                long endTime = System.nanoTime();
                totalSortedTime += (endTime - startTime);
            }
            long avgSortedTime = totalSortedTime / GET_OPERATIONS;

            System.out.printf("%-15d | %-25d | %-25d\n", size, avgUnsortedTime, avgSortedTime);
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Final result holder value (to prevent JIT optimization): " + resultHolder);
    }
}
