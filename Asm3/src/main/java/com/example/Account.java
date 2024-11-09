package com.example;

import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String accountNumber;
    private double balance;
    private String typeAccount;

    

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public String getTypeAccount() {
        return typeAccount;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public boolean isPrimiumAccount() {
        return balance >= 10000000 ? true : false;
    }
    
    public String fomatBalance(double balance) {
        Locale Vietnam = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(Vietnam);
        String fomattedBalance = nf.format(balance);
        return fomattedBalance;
    }

    public String getDateTime() {
        LocalDateTime currenDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fomattedDateTime = currenDateTime.format(formatter);
        return fomattedDateTime;
    }

    @Override
    public String toString() {
        String balanceStr = fomatBalance(this.getBalance());
        String inforAccount = String.format("%-21s | %10s | %12s%30s", accountNumber, this.getTypeAccount() , " ", balanceStr);
        return inforAccount;
    }
}
