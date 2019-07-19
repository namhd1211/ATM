package com.company.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private String balance;
    private String amount;
    private LocalDateTime dateTime;

    public Transaction() {
    }

    public Transaction(String type, String balance, String amount, LocalDateTime dateTime) {
        this.type = type;
        this.balance = balance;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Type: '" + type + '\'' +
                ", Balance: '" + balance + '\'' +
                ", Amount: '" + amount + '\'' +
                ", DateTime: " + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                '}';
    }
}
