package com.codsoft.banking.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime dateTime;

    public Transaction(
            String type,
            double amount,
            double balanceAfter) {

        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return String.format(
                "%s | %-18s | Amount: ₹%.2f | Balance: ₹%.2f",
                dateTime.format(formatter),
                type,
                amount,
                balanceAfter
        );
    }
}