package com.example;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Bank {
    private String bankID;
    private String bankName;

    public static boolean checkAccountNum(String acNum) {
        if (acNum.length() != 6)
            return false;
        for (int i = 0; i < acNum.length(); i++) {
            if (!Character.isDigit(acNum.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean kiemTraMaCCCD(String s) {
        if (s.length() != 10)
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean checkDauVao(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    public Bank(String bankID, String bankName) {
        this.bankID = bankID;
        this.bankName = bankName;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void addCustomers(String fileName) {
        List<Customer> customers = CustomerDao.list();
        List<List<String>> userStr = TextFileService.readFile(fileName);
        for (List<String> list : userStr) {
            String cusNum = list.get(0);
            if (!kiemTraMaCCCD(cusNum)) {
                System.out.println("ma sai");
                continue;
            }
            String cusName = list.get(1);
            Customer newCustomer = new Customer(cusNum, cusName);
            if (isCustomerExisted(customers, newCustomer)) {
                System.out.println("khach hang " + cusNum + " da ton tai");
            } else {
                customers.add(newCustomer);
            }
        }
        try {
            CustomerDao.save(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSavingAccount(Scanner scanner, String customerId) {
        List<Account> accounts = AccountDao.list();
        if (!kiemTraMaCCCD(customerId)) {
            System.out.println("khach hang khong ton tai");
            return;
        }
        System.out.print("nhap so tai khoan gom 6 chu so: ");
        String accountID = scanner.nextLine();
        while (!checkAccountNum(accountID)) {
            System.out.print("nhap so tai khoan gom 6 chu so: ");
            accountID = scanner.nextLine();
        }
        System.out.print("nhap so du tai khoan >= 50000đ: ");
        String balanceStr = scanner.nextLine();
        while (Double.parseDouble(balanceStr) < 50000 || (!checkDauVao(balanceStr))) {
            System.out.print("nhap so du tai khoan >= 50000đ: ");
            balanceStr = scanner.nextLine();
        }
        Account savingAccount = new SavingAccount(customerId, accountID, Double.parseDouble(balanceStr));
        if (isAccountExisted(accounts, savingAccount)) {
            System.out.println("tai khoan da ton tai!");
            return;
        }
        accounts.add(savingAccount);
        try {
            AccountDao.save(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCustomerExisted(List<Customer> customers, Customer newCustomer) {
        for (Customer customer : customers) {
            if (customer.getID().equals(newCustomer.getID())) {
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(Scanner scanner, String customerId) {
        List<Account> accounts = AccountDao.list();
        if (!kiemTraMaCCCD(customerId)) {
            System.out.println("khach hang khong ton tai!");
            return false;
        }
        Customer customer = getCustomerById(CustomerDao.list(), customerId);
        System.out.println(customer.cusomerInfor());
        return customer.withdraw(scanner);
    }

    public static boolean isAccountExisted(List<Account> accountsList, Account newAccount) {
        for (Account account : accountsList) {
            if (account.getAccountNum().equals(newAccount.getAccountNum())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAccountExisted1(List<Account> accounts, String accuountNum) {
        for (Account account : accounts) {
            if (account.getAccountNum().equals(accuountNum)) {
                return true;
            }
        }
        return false;
    }


    public static Customer getCustomerById(List<Customer> customerList, String customerId) {
        for (Customer customer : customerList) {
            if (customer.getID().equals(customerId)) {
                return customer;
            }
        }
        System.out.println("khong ton tai khach hang voi Id " + customerId);
        return null;
    }

    public static Account getAccountById(String accountId) {
        for (Account account : AccountDao.list()) {
            if (account.getAccountNum().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public void showCustomers() {
        List<Customer> customers = CustomerDao.list();
        if (customers.isEmpty()) {
            System.out.println("Chua co khach hang nao trong danh sach!");
            return;
        }
        for (Customer customer : customers) {
            System.out.println(customer.cusomerInfor());
        }
    }

    void tranfers(Scanner scanner, String customerId) {
        Customer customer = getCustomerById(CustomerDao.list(), customerId);
        if (!kiemTraMaCCCD(customerId) || customer == null) {
            System.out.println("khach hang khong ton tai!");
            return;
        }
        System.out.println(customer.cusomerInfor());
        customer.transfers(scanner);
    }
}
