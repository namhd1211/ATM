package com.company.entity;

public class WithDraw {
    private String accountNumber;
    private String balance;
    private String withDrawAmount;
    private String createdDate;

    public WithDraw(String accountNumber, String balance, String withDrawAmount, String createdDate) {
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
