/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author diogodias
 */
public class Food {

    private final String name;
    private final double weight;
    private final LocalDate bestBefore;
    private final LocalDateTime storageTime;

    public Food(String name, double weight,
            LocalDate bestBefore, LocalDateTime storageTime) {

        Objects.requireNonNull(name, "Food name cannot be null");
        this.name = name.trim();

        if (this.name.isEmpty()) {
            throw new IllegalArgumentException(
                    "Food name cannot be empty."
            );
        }

        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException(
                    "Weight must be a positive number."
            );
        }

        this.weight = weight;
        this.bestBefore = Objects.requireNonNull(
                bestBefore, "Best-before date cannot be null"
        );
        this.storageTime = Objects.requireNonNull(
                storageTime, "Storage time cannot be null"
        );
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public LocalDateTime getStorageTime() {
        return storageTime;
    }

    @Override
    public String toString() {
        return "Food: " + name
             + " | Weight: " + weight
             + " | Best before: " + bestBefore
             + " | Stored at: " + storageTime;
    }
}
