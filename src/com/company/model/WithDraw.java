package com.company.model;

import java.time.LocalDateTime;

public class WithDraw {
    private String accountNumber;
    private String balance;
    private String withDrawAmount;
    private LocalDateTime createdDate;

    public WithDraw(String accountNumber, String balance, String withDrawAmount, LocalDateTime createdDate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.withDrawAmount = withDrawAmount;
        this.createdDate = createdDate;
    }

    public String getWithDrawAmount() {
        return withDrawAmount;
    }

    public void setWithDrawAmount(String withDrawAmount) {
        this.withDrawAmount = withDrawAmount;
    }

    public WithDraw() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
