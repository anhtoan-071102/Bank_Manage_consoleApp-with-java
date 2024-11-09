package com.example;

import java.util.ArrayList;

public class DigitalCustomer extends Customer{

    public DigitalCustomer(String _name, String _ID) {
        super(_name, _ID);
    }

    @Override
    public String getCustomerID() {
        // TODO Auto-generated method stub
        return super.getCustomerID();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public void setCustomerID(String customerID) {
        // TODO Auto-generated method stub
        super.setCustomerID(customerID);
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name);
    }

    

    @Override
    public
    ArrayList<Account> addAccount(Account a) {
        // TODO Auto-generated method stub
        return super.addAccount(a);
    }

    @Override
    public
    String cusomerInfor() {
        // TODO Auto-generated method stub
        return super.cusomerInfor();
    }

    @Override
    ArrayList<Account> deleteAcount(Account a) {
        // TODO Auto-generated method stub
        return super.deleteAcount(a);
    }

    @Override
    public Account getAccountById(String accountID) {
        // TODO Auto-generated method stub
        return super.getAccountById(accountID);
    }

    @Override
    public ArrayList<Account> getAcounts() {
        // TODO Auto-generated method stub
        return super.getAcounts();
    }

    @Override
    String getTotalBalance() {
        // TODO Auto-generated method stub
        return super.getTotalBalance();
    }

    @Override
    double totalAmount() {
        // TODO Auto-generated method stub
        return super.totalAmount();
    }

    @Override
    public
    boolean isExistedAccount(String acocuntNumber) {
        // TODO Auto-generated method stub
        return super.isExistedAccount(acocuntNumber);
    }

    @Override
    public boolean isPremiumCustomer() {
        // TODO Auto-generated method stub
        return super.isPremiumCustomer();
    }

    @Override
    public void setAcounts(ArrayList<Account> acounts) {
        // TODO Auto-generated method stub
        super.setAcounts(acounts);
    }

    public boolean withdraw(String accountNmber, double amount) {
        for (Account account : this.getAcounts()) {
            if (account.getAccountNumber().equals(accountNmber)) {
                if (account.getTypeAccount().equals("SAVINGS")) {
                    SavingAccount savingAccount = (SavingAccount) account;
                    savingAccount.withdraw(amount);
                } else {
                    LoansAccount loansAccount = (LoansAccount) account;
                    loansAccount.withdraw(amount);
                }
                return true;
            }
        }
        return false;
    }
}
