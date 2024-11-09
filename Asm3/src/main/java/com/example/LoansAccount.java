package com.example;

import java.util.ArrayList;
import java.util.List;

public class LoansAccount extends Account implements Withdraw, ReportService{

    String dateTime = "";
    private List<Transaction> logs;

    

    public List<Transaction> getLogs() {
        return logs;
    }


    public void setLogs(List<Transaction> logs) {
        this.logs = logs;
    }


    public String getDaTime() {
        return dateTime;
    }
    

    @Override
    public String getDateTime() {
        // TODO Auto-generated method stub
        return super.getDateTime();
    }


    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public LoansAccount(String _acNum, double _balance) {
        super(_acNum, _balance);
        this.setTypeAccount("LOAN");
        this.logs = new ArrayList<>();
    }

    public LoansAccount(Account account) {
        super(account.getAccountNumber(), account.getBalance());
        this.setTypeAccount("LOAN");
        this.logs = new ArrayList<>();
    }

    final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE  = 0.01;
    final double LOAN_ACCOUNT_MAX_BALANCE  = 100000000;
    
    

    @Override
    public String getAccountNumber() {
        // TODO Auto-generated method stub
        return super.getAccountNumber();
    }

    @Override
    public boolean isPrimiumAccount() {
        // TODO Auto-generated method stub
        return super.isPrimiumAccount();
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        // TODO Auto-generated method stub
        super.setAccountNumber(accountNumber);
    }

    @Override
    public void setBalance(double balance) {
        // TODO Auto-generated method stub
        super.setBalance(balance);
    }

    @Override
    public void setTypeAccount(String typeAccount) {
        // TODO Auto-generated method stub
        super.setTypeAccount(typeAccount);
    }

    @Override
    public boolean isAccepted(double amount) {
        if (this.getBalance() < amount + 50000 || amount >  LOAN_ACCOUNT_MAX_BALANCE)  {
            return false;
        }
        return true;
    }

    
    @Override
    public double getBalance() {
        // TODO Auto-generated method stub
        return super.getBalance();
    }

    @Override
    public String getTypeAccount() {
        // TODO Auto-generated method stub
        return super.getTypeAccount();
    }

    @Override
    public boolean withdraw(double amount) {
        double newBalance = 0.0;
        if (isAccepted(amount)) {
            double outMoney = 0.0;
            if (this.isPrimiumAccount()) {
                outMoney += amount * (1 + LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE);
            } else {
                outMoney += amount * (1 + LOAN_ACCOUNT_WITHDRAW_FEE);
            }
            newBalance = this.getBalance() - outMoney;
            this.setBalance(newBalance);
            dateTime = this.getDateTime();
            Transaction newTransaction = new Transaction(this.getAccountNumber(), amount, this.fomatBalance(amount), dateTime);  
            this.getLogs().add(newTransaction);
            return true;
        }
        return false;
    }

    // @Override 
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
        double feeAndVatNum = this.isPrimiumAccount() ? transaction.getAmount() * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : transaction.getAmount() * LOAN_ACCOUNT_WITHDRAW_FEE;
        String feeAndVat = String.format("PHI + VAT: %27s%n", this.fomatBalance(feeAndVatNum));
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
