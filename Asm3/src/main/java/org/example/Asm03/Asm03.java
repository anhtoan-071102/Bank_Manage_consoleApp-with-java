package org.example.Asm03;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.example.Account;
import com.example.DigitalBank;
import com.example.DigitalCustomer;
import com.example.LoansAccount;
import com.example.SavingAccount;
import com.example.Transaction;

import java.util.ArrayList;

public class Asm03 {

    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "FUNIX";
    static List<DigitalCustomer> digitalCustomers = new ArrayList<>();
    static int numberOfCreat = 0;
    static int withDrawNum = 0;

    public static boolean kiemTraMaCCCD(String s) {
        if (s.length() != 12)
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean checkAccountNum(String acNum) {
        if (acNum.length() != 6)
            return false;
        for (int i = 0; i < acNum.length(); i++) {
            if (!Character.isDigit(acNum.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean checkStringInList(String a, List<String> stringList) {
        return stringList.stream().anyMatch(s -> s.equalsIgnoreCase(a));
    }

    static void showCustomerInfor() {
        if (numberOfCreat == 0) {
            activeBank.setCustomers(digitalCustomers);
            activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
            numberOfCreat++;
        }
        System.out.println(
                "+-----------------------+---------------------------------------------+-----------------------+");
        for (DigitalCustomer customer : activeBank.getCustomers()) {
            System.out.println(customer.cusomerInfor());
        }
        System.out.println(
                "+-----------------------+---------------------------------------------+-----------------------+");
    }

    static void showCustomerInfor(String cutomerID) {
        System.out.println(
                "+-----------------------+---------------------------------------------+-----------------------+");
        for (DigitalCustomer customer : activeBank.getCustomers()) {
            if (customer.getCustomerID().equals(cutomerID)) {
                System.out.println(customer.cusomerInfor());
                break;
            }
        }
        System.out.println(
                "+-----------------------+---------------------------------------------+-----------------------+");
    }

    static DigitalBank addAtmAccount() {
        String in = "";
        System.out.println("enter customer ID");
        in = sc.nextLine();
        while (!kiemTraMaCCCD(in)) {
            System.out.println("ID wrong, please re-enter or no to exit");
            in = sc.nextLine();
            if (in.equals("no")) {
                return activeBank;
            }
        }
        if (!activeBank.isCustomerExisted(in)) {
            System.out.println("no cutomer with id " + in);
            return activeBank;
        }
        System.out.println("enter new account number");
        String in1 = sc.nextLine();
        while (!checkAccountNum(in1)) {
            System.out.println("accountNum wrong, please re-enter or no to exit");
            in1 = sc.nextLine();
            if (in1.equals("no")) {
                return activeBank;
            }
        }
        if (activeBank.getCustomerById(in).isExistedAccount(in1)) {
            System.out.println("account is existed");
            return activeBank;
        }
        System.out.println("enter balance");
        double balan = Double.parseDouble(sc.nextLine());
        LoansAccount newAccount = new LoansAccount(in1, balan);
        activeBank.getCustomerById(in).addAccount(newAccount);
        return activeBank;
    }

    static DigitalBank addSavingAccount() {
        String in = "";
        System.out.println("enter customer ID");
        in = sc.nextLine();
        while (!kiemTraMaCCCD(in)) {
            System.out.println("ID wrong, please re-enter or no to exit");
            in = sc.nextLine();
            if (in.equals("no")) {
                return activeBank;
            }
        }
        if (!activeBank.isCustomerExisted(in)) {
            System.out.println("no cutomer with id " + in);
            return activeBank;
        }
        System.out.println("enter new account number");
        String in1 = sc.nextLine();
        while (!checkAccountNum(in1)) {
            System.out.println("accountNum wrong, please re-enter or no to exit");
            in1 = sc.nextLine();
            if (in1.equals("no")) {
                return activeBank;
            }
        }
        if (activeBank.getCustomerById(in).isExistedAccount(in1)) {
            System.out.println("account is existed");
            return activeBank;
        }
        System.out.println("enter balance");
        double balan = Double.parseDouble(sc.nextLine());
        SavingAccount newAccount = new SavingAccount(in1, balan);
        activeBank.getCustomerById(in).addAccount(newAccount);
        return activeBank;
    }

    static void withDraw() {
        String in = "";
        System.out.println("enter customer ID");
        in = sc.nextLine();
        while (!kiemTraMaCCCD(in)) {
            System.out.println("ID wrong, please re-enter or no to exit");
            in = sc.nextLine();
            if (in.equals("no")) {
                return;
            }
        }
        if (!activeBank.isCustomerExisted(in)) {
            System.out.println("no cutomer with id " + in);
            return;
        }
        System.out.println("enter account number");
        String in1 = sc.nextLine();
        while (!checkAccountNum(in1)) {
            System.out.println("accountNum wrong, please re-enter or no to exit");
            in1 = sc.nextLine();
            if (in1.equals("no")) {
                return;
            }
        }
        System.out.println("enter number of money");
        double amount = Double.parseDouble(sc.nextLine());
        DigitalCustomer digitalCustomer = activeBank.getCustomerById(in);
        boolean check = digitalCustomer.withdraw(in1, amount);
        if (check) {
            System.out.println("successful!");
            withDrawNum += 1;
        } else {
            System.out.println("non-successful");
        }
    }

    static void showLog() {
        String in = "";
        System.out.println("enter customer ID");
        in = sc.nextLine();
        while (!kiemTraMaCCCD(in)) {
            System.out.println("ID wrong, please re-enter or no to exit");
            in = sc.nextLine();
            if (in.equals("no")) {
                return;
            }
        }
        if (!activeBank.isCustomerExisted(in)) {
            System.out.println("no cutomer with id " + in);
            return;
        }
        if (withDrawNum != 0) {
            showCustomerInfor(in);
            System.out.println();
            String tiTle = String.format("%-18s | %-25s | %-30s | %-45s%n", "Account", "Amount", "Time",
                    "Transaction ID");
            System.out.println(tiTle);
        }
        DigitalCustomer customer = activeBank.getCustomerById(in);
        List<Account> accounts = customer.getAcounts();
        for (Account account : accounts) {
            if (account.getTypeAccount().equals("SAVINGS")) {
                SavingAccount savingAccount = (SavingAccount) account;
                savingAccount.log();
            } else {
                LoansAccount loansAccount = (LoansAccount) account;
                loansAccount.log();
            }
        }
    }

    static void showLogDetails(DigitalCustomer digitalCustomer) {
        if (!activeBank.isCustomerExisted(digitalCustomer.getCustomerID())) {
            System.out.println("no cutomer with id " + digitalCustomer.getCustomerID());
            return;
        }
        ArrayList<Account> accounts = digitalCustomer.getAcounts();
        List<Transaction> trans = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getTypeAccount().equals("LOAN")) {
                LoansAccount loansAccount = (LoansAccount) account;
                for (Transaction transaction : loansAccount.getLogs()) {
                    trans.add(transaction);
                }
                loansAccount.log();
            } else {
                SavingAccount savingAccount = (SavingAccount) account;
                for (Transaction transaction : savingAccount.getLogs()) {
                    trans.add(transaction);
                }
                savingAccount.log();
            }
        }
        if (trans.size() > 0) {
            System.out.println("choose transaction (enter the order 1/2/...)");
            String in = sc.nextLine();
            int maxTran = trans.size();
            while (Integer.parseInt(in) > maxTran || Integer.parseInt(in) <= 0) {
                System.out.println("wrong, please re-enter or no to exit");
                in = sc.nextLine();
                if (in.equals("no")) {
                    return;
                }
            }
            Transaction transaction1 = trans.get(Integer.parseInt(in) - 1);
            Account account = digitalCustomer.getAccountById(transaction1.getAccountNum());
            if (account.getTypeAccount().equals("LOAN")) {
                LoansAccount loansAccount = (LoansAccount) account;
                loansAccount.logDetails(transaction1);
            } else {
                SavingAccount savingAccount = (SavingAccount) account;
                savingAccount.logDetails(transaction1);
            }
        } else {
            System.out.println("no transaction");
            return;
        }
    }

    static void Menu() {
        boolean out = true;
        while (out) {
            System.out.println("+----------+-----------------------------------------------+----------+");
            System.out.println("| NGAN HANG DIEN TU |  FX22602@v3.0.0                                 |");
            System.out.println("1. Thong tin khach hang                                               |");
            System.out.println("2. Them tai khoan ATM                                                 |");
            System.out.println("3. Them tai khoan tin dung                                            |");
            System.out.println("4. Rut tien                                                           |");
            System.out.println("5. Lich su giao dich                                                  |");
            System.out.println("6. Chi tiet giao dich                                                 |");
            System.out.println("0. Thoat                                                              |");
            System.out.println("+----------+-----------------------------------------------+----------+");

            String in = "";
            System.out.println("enter your choose");
            in = sc.nextLine();
            List<String> chooses = Arrays.asList("0", "1", "2", "3", "4", "5", "6");
            while (!checkStringInList(in, chooses)) {
                System.out.println("wrong, enter again");
                in = sc.nextLine();
            }
            switch (in) {
                case "1":
                    System.out.println("CHuc nang 1. Xem thong tin khach hang: ");
                    showCustomerInfor();
                    break;
                case "2":
                    System.out.println("CHuc nang 2. Them tai khoan ATM: ");
                    addAtmAccount();
                    System.out.println("sucessful!");
                    break;
                case "3":
                    System.out.println("CHuc nang 3. Them tai khoan tin dung: ");
                    addSavingAccount();
                    System.out.println("sucessful!");
                    break;
                case "4":
                    System.out.println("Chuc nang 4, Rut tien: ");
                    withDraw();
                    break;
                case "5":
                    System.out.println("Chuc nang 5, Lich su giao dich: ");
                    showLog();
                    break;
                case "6":
                    try {
                        DigitalCustomer customer = activeBank.getCustomerById(CUSTOMER_ID);
                        showLogDetails(customer);
                        break;
                    } catch (Exception e) {
                        System.out.println("no customer in bank");
                        break;
                    }

                default:
                    out = false;
                    System.out.println("good bye");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Menu();
    }
}
