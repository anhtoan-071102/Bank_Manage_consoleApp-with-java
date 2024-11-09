package com.example;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;



public class SavingAccount extends Account implements Withdraw, ReportService{

    private List<Transaction> logs;
    String dateTime = "";
    public List<Transaction> getLogs() {
        return logs;
    } 

    public String getDaTime() {
        return dateTime;
    }

    public void setLogs(List<Transaction> logs) {
        this.logs = logs;
    }

    public SavingAccount(String _acNum, double _balance) {
        super(_acNum, _balance);
        this.setTypeAccount("SAVINGS");
        this.logs = new ArrayList<>();
    }

    public SavingAccount(Account account) {
        super(account.getAccountNumber(), account.getBalance());
        this.setTypeAccount("SAVINGS");
        this.logs = new ArrayList<>();
    }

    final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    @Override
    public String toString() {
        Locale Vietnam = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(Vietnam);
        String soDu1 = nf.format(this.getBalance());
        String TTTK = String.format("%-25s |%20s| %25s%30s", this.getAccountNumber(), "SAVINGS", " ", soDu1);
        return TTTK;
    }

    @Override
    public boolean isAccepted(double amount) {
        if (this.getBalance() < amount + 50000 || ((amount > 5000000) && (!this.isPrimiumAccount())) || amount >  SAVINGS_ACCOUNT_MAX_WITHDRAW || amount % 10000 != 0)  {
            return false;
        }
        return true;
    }

    @Override 
    public boolean withdraw(double amount) {
        double newBalance = 0.0;
        if (isAccepted(amount)) {
            newBalance = this.getBalance() - amount;
            this.setBalance(newBalance);
            dateTime = this.getDateTime();
            Transaction newTransaction = new Transaction(this.getAccountNumber(), amount, this.fomatBalance(amount), dateTime);  
            this.getLogs().add(newTransaction);
            return true;
        }
        return false;
    }

    public void log() {
        StringBuffer stBuffer = new StringBuffer();
        for (Transaction transaction : this.getLogs()) {
            String accountNum = String.format("[GD]    %-10s |", transaction.getAccountNum());
            String amoutStr = String.format("%-25s", transaction.getMount());
            String timeStr = String.format("%-30s", transaction.getTime());
            String trasactionID = String.format("%-45s%n", transaction.getID());

            stBuffer.append(accountNum);
            stBuffer.append(amoutStr);
            stBuffer.append(timeStr);
            stBuffer.append(trasactionID);
        }

        
        System.out.println(stBuffer.toString());
    }

    public void logDetails(Transaction tran) {
        Transaction transaction = new Transaction();
        for (Transaction transaction1 : this.getLogs()) {
            if (transaction1.getID().equals(tran.getID())) {
                transaction = transaction1;
                break;
            }
        }

        StringBuffer stBuffer = new StringBuffer();
        String divider = "+----------+-------------------------+----------+\n";
        String title = String.format("%30s%n" ,"BIEN LAI GIAO DICH SAVINGS");
        String DateTime = String.format("NGAY G/D: %28s%n", transaction.getTime());
        String bankId = String.format("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2022");
        String accountNum = String.format("SO TK: %31s%n", transaction.getAccountNum());
        String moneyOut = String.format("SO TIEN: %29s%n", transaction.getMount());
        String newBalance = String.format("SO DU: %31s%n", this.fomatBalance(this.getBalance()));
        String feeAndVat = String.format("PHI + VAT: %27s%n", "0đ");
        stBuffer.append(divider);
        stBuffer.append(title);
        stBuffer.append(DateTime);
        stBuffer.append(bankId);
        stBuffer.append(accountNum);
        stBuffer.append(moneyOut);
        stBuffer.append(newBalance);
        stBuffer.append(feeAndVat);
        stBuffer.append(divider);
        System.out.println(stBuffer.toString());
    }

    @Override
    public void log(double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'log'");
    }
}
