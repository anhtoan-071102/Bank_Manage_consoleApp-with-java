package com.example;

import java.io.Serializable;

public class Account implements Serializable{
    private static final long serialVersionUID = 2L;
    private String cusID;
    private String accountNum;
    private double balance;

    public Account(String cusID, String accountNum, double balance) {
        this.cusID = cusID;
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public Account() {
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }
}
