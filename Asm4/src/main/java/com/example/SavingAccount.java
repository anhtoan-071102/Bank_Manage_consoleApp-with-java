package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;

public class SavingAccount extends Account implements ITransfer{
    
    public SavingAccount(String cusID, String accountNum, double balance) {
        super(cusID, accountNum, balance);
        //TODO Auto-generated constructor stub
    }

    public SavingAccount() {
    }

    boolean isAccountPremium() {
        if (this.getBalance() < 10000000) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    String fomatMoney(double money) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        return numberFormat.format(money);
    }

    String fomatTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(dateTimeFormatter);
    }

    boolean isAccept(double amount) {
        if (this.getBalance() < amount + 50000 || ((amount > 5000000) && (!this.isAccountPremium())) || amount < 50000 || amount % 10 != 0) {
            return false;
        }
        return true;
    }

    boolean withDraw(double amount) throws IOException {
        if (!isAccept(amount)) {
            return false;
        }
        String dateTime = fomatTime();
        creatTransaction(amount, dateTime, true, TransactionType.WITHDRAW);
        IReport iReport = new IReport();
        iReport.setAccountNum(String.format("SO TK: %31s%n", this.getAccountNum()));
        iReport.setDateTime(String.format("NGAY G/D: %28s%n", dateTime));
        iReport.setMoneyOut(String.format("SO TIEN RUT: %23s%n", fomatMoney(amount)));
        iReport.setNewBalance(String.format("SO DU: %31s%n", fomatMoney(this.getBalance())));
        System.out.println("Rut tien thanh cong, bien lai giao dich: ");
        System.out.println(iReport.log(amount, TransactionType.WITHDRAW, null)); 
        return true;
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = TransactionDao.list();
        List<Transaction> outTransactions = new ArrayList<>();
        try {
            for (Transaction transaction : transactions) {
                if (transaction.getAccountNum().equals(this.getAccountNum())) {
                    outTransactions.add(transaction);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return outTransactions;
    }

    public Customer getCustomer() {
        try {
            for (Customer customer : CustomerDao.list()) {
                if (customer.getID().equals(this.getCusID())) {
                    return customer;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public void displayTransactionsList() {
        System.out.println(accountInfor());
        for (Transaction transaction : getTransactions()) {
            System.out.println(transaction.toString());
        }
    }

    public void creatTransaction(double amount, String time, boolean status, TransactionType type) throws IOException {
        List<Transaction>  transactions = TransactionDao.list();
        Transaction transaction = new Transaction(this.getAccountNum() , amount, fomatMoney(amount), time, type);
        transactions.add(transaction);
        TransactionDao.save(transactions);
        if (transaction.getType().getType().equals("deposit")) {
            this.setBalance(this.getBalance() + amount);
        } else {
            this.setBalance(this.getBalance() - amount);
        }
        AccountDao.update(this);
    }

    public void creatTransaction1(Account newAccount, double amount, String time, boolean status, TransactionType type) throws IOException {
        List<Transaction>  transactions = TransactionDao.list();
        // Account account = new Account(this.getCusID(), acNum, 0);
        Transaction transaction = new Transaction(newAccount.getAccountNum() , amount, fomatMoney(amount), time, type);
        transactions.add(transaction);
        TransactionDao.save(transactions);
        newAccount.setBalance(newAccount.getBalance() + amount);
        AccountDao.update(newAccount);
    }

    public void input(Scanner scanner) {
        System.out.print("Nhap so tai khoan: ");
        String accountNum = scanner.nextLine();
        while (!Bank.checkAccountNum(accountNum) || Bank.isAccountExisted1(null, accountNum)) {
            System.out.print("khong hop le, moi nhap lai: ");
            accountNum = scanner.nextLine();
        }
        System.out.print("nhap so tien ban dau: ");
        String firstMoney = scanner.nextLine();
        while (!Bank.checkDauVao(firstMoney)) {
            System.out.print("khong hop le, moi nhap lai: ");
            firstMoney = scanner.nextLine();
        }
        Account newAccount = new Account(this.getCusID(), accountNum, 0);
        try {
            creatTransaction1(newAccount, Double.parseDouble(firstMoney), fomatTime(), true, TransactionType.DEPOSIT);
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    String accountInfor() {
        String balanceStr = fomatMoney(this.getBalance());
        String inforAccount = String.format("%-21s | %-50s | %12s%30s", this.getAccountNum(), "SAVINGS" , " ", balanceStr);
        return inforAccount;
    }

    @Override
    public void transfer(Account receiveAccount, double amounts) throws IOException {
        if (this.getBalance() < 50000) {
            return;
        }
        String time = fomatTime();
        creatTransaction(amounts, time, true, TransactionType.TRANSFER);
        creatTransaction1(receiveAccount, amounts, time, true, TransactionType.DEPOSIT);
        IReport iReport = new IReport();
        iReport.setAccountNum(String.format("SO TK: %31s%n", this.getAccountNum()));
        iReport.setDateTime(String.format("NGAY G/D: %28s%n", time));
        iReport.setMoneyOut(String.format("SO TIEN RUT: %23s%n", fomatMoney(amounts)));
        iReport.setNewBalance(String.format("SO DU: %31s%n", fomatMoney(this.getBalance())));
        System.out.println("Chuyen tien thanh cong, bien lai giao dich: ");
        System.out.println(iReport.log(amounts, TransactionType.TRANSFER, null)); 
        // throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    }
}
