package com.example;

import java.util.UUID;

public class Transaction {
    private String ID;
    String accountNum;
    double amount;
    String mount;
    String Time;
    public Transaction(String accountNum, double amount, String mount, String time) {
        this.ID = UUID.randomUUID().toString();
        this.accountNum = accountNum;
        this.amount = amount;
        this.mount = mount;
        Time = time;
    }

    public Transaction() {}

    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountNum() {
        return accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
    public String getMount() {
        return mount;
    }
    public void setMount(String mount) {
        this.mount = mount;
    }
    public String getTime() {
        return Time;
    }
    public void setTime(String time) {
        Time = time;
    }
}
