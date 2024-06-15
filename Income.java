package com.example.assignment1;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {
    private int id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;

    public Income(int id, String description, BigDecimal amount, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
