package com.company.entity;

public class Transfer {
    private String srcAccNumber;
    private String desAccNumber;
    private String balance;
    private String createdDate;

    public Transfer() {
    }

    public Transfer(String srcAccNumber, String desAccNumber, String balance, String createdDate) {
        this.srcAccNumber = srcAccNumber;
        this.desAccNumber = desAccNumber;
        this.balance = balance;
        this.createdDate = createdDate;
    }

    public String getSrcAccNumber() {
        return srcAccNumber;
    }

    public void setSrcAccNumber(String srcAccNumber) {
        this.srcAccNumber = srcAccNumber;
    }

    public String getDesAccNumber() {
        return desAccNumber;
    }

    public void setDesAccNumber(String desAccNumber) {
        this.desAccNumber = desAccNumber;
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
