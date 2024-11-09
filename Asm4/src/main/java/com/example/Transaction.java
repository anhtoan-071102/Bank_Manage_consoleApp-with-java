package com.example;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

public class Transaction implements Serializable{
    private static final long serialVersionUID = 2L;
    private String accountNum;
    private double amount;
    private String amString;
    private String dateTime;
    private String tranID;
    private TransactionType type;

    public Transaction(String accountNum, double amount, String amString, String dateTime, TransactionType type) {
        this.tranID = UUID.randomUUID().toString();
        this.accountNum = accountNum;
        this.amount = amount;
        this.amString = amString;
        this.dateTime = dateTime;
        this.type = type;
    }

    public String getAccountNum() {
        return accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getAmString() {
        return amString;
    }
    public void setAmString(String amString) {
        this.amString = amString;
    }
    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String getTranID() {
        return tranID;
    }
    public void setTranID(String tranID) {
        this.tranID = tranID;
    }
    public TransactionType getType() {
        return type;
    }

    public String toString() {
        @SuppressWarnings("deprecation")
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        String amountStr = numberFormat.format(amount);
        String out;
        if (this.getType().getType().equals("deposit")) {
            out = String.format("[GD] %s | %-10s   | +%-23s |   /%s", this.getAccountNum(), this.getType().getType(), amountStr, this.getDateTime());
        } else {
            out = String.format("[GD] %s | %-10s   | -%-23s |   /%s", this.getAccountNum(), this.getType().getType(), amountStr, this.getDateTime());
        }
        return out;
    }
}
