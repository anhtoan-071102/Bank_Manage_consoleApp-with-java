package com.example;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public abstract class Customer extends User {
    Scanner sc = new Scanner(System.in);
    public Customer(String _name, String _ID) {
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

    private ArrayList<Account> acounts = new ArrayList<>();

    public boolean isPremiumCustomer() {
        for (Account account : acounts) {
            if (account.getBalance() >= 10000000) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Account> getAcounts() {
        return acounts;
    }

    public void setAcounts(ArrayList<Account> acounts) {
        this.acounts = acounts;
    }

    boolean isExistedAccount(String acocuntNumber) {
        if (this.getAcounts().isEmpty()) {
            return false;
        }
        for (Account account : acounts) 
        {
            if (account.getAccountNumber().equals(acocuntNumber)) {
                return true;
            }
        }
        return false;
    }

    Account getAccountById (String accountID) {
        Account account1 = null;
        for (Account account : acounts) {
            if (account.getAccountNumber().equals(accountID)) {
                account1 = account;
                break;
            }
        }
        return account1;
    }
    

    ArrayList<Account> addAccount(Account a) {
        for (Account account : acounts) 
        {
            if (account.getAccountNumber().equals((a.getAccountNumber()))) {
                System.out.println("Acount " + a.getAccountNumber() + "existed");
                return acounts;
            }
        }
        acounts.add(a);
        return acounts;
    }

    ArrayList<Account> deleteAcount(Account a) {
        if (acounts.contains(a)) {
            System.out.println("ban muon xoa tai khoan " + a.getAccountNumber() + " ?    Yes/No");
            Scanner sc = new Scanner(System.in);
            String luachon = sc.nextLine();
            while (!luachon.equals("Yes") || !luachon.equals("No")) {
                System.out.println("Nhap sai moi nhap (Yes) hoac (No)");
                luachon = sc.nextLine();
            }
            if (luachon.equals("Yes")) {
                acounts.remove(a);
                System.out.println("Completed");
            } else {
                return acounts;
            }
        } else {
            System.out.println("don't have acount,check again");
        }
        return acounts;
    }

    String getTotalBalance() {
        double total = 0;
        for (Account account : acounts) {
            total += account.getBalance();
        }
        Locale Vietnam = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(Vietnam);
        String totalBalance = nf.format(total);
        return totalBalance;
    }

    double totalAmount() {
        double total = 0;
        for (Account account : acounts) {
            total += account.getBalance();
        }
        return total;
    }

    String cusomerInfor() {
        String customerInformation = String.format("%-30s | %10s |", this.getCustomerID(), this.getName());
        StringBuilder inFor = new StringBuilder(customerInformation);
        if (isPremiumCustomer())
            inFor.append(String.format("%10s | %28s", "Premium", this.getTotalBalance()));
        else
            inFor.append(String.format("%10s | %28s", "Normal", this.getTotalBalance()));
        for (int i = 0; i < this.acounts.size(); i++) {
            inFor.append("\n").append(String.format("%-5d%s | %s |", (i+1), " ", this.getAcounts().get(i).toString()));
        }
        return inFor.toString();
    }
}
