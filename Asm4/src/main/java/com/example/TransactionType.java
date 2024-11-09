package com.example;

public enum TransactionType {
    WITHDRAW("withdraw"), 
    TRANSFER("transfer"), 
    DEPOSIT("deposit");

    private final String type;
    TransactionType(String type) {
        //TODO Auto-generated constructor stub
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
