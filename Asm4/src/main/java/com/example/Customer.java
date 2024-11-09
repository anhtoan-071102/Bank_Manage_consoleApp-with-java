package com.example;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Customer extends User {
    public Customer(String iD, String name) {
        super(iD, name);
    }

    public Customer() {}

    public Customer(List<String> values) {
        this(values.get(0), values.get(1));
    }

    @Override
    public String getID() {
        return super.getID();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setID(String iD) {
        super.setID(iD);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    double getTotalBalance() {
        List<Account> accounts = AccountDao.list();
        double totalBalance = 0.0;
        if (accounts.isEmpty()) {
            return totalBalance;
        }
        for (Account account : accounts) {
            SavingAccount savingAccount = (SavingAccount) account;
            if (savingAccount.getCusID().equals(this.getID())) {
                totalBalance += savingAccount.getBalance();
            }
        }
        return totalBalance;
    }

    boolean isPremiumCustomer() {
        if (this.getTotalBalance() > 10000000)  {
            return true;
        }
        return false;
    }

    public Account getAccountById(List<Account> accounts, String acNum) {
        for (Account account : accounts) {
            if (account.getAccountNum().equals(acNum)) {
                return account;
            }
        }
        System.out.println("khong ton tai khach hang voi Id " + acNum);
        return null;
    }

    


    // List<SavingAccount> removeAccount(String accountNum) {
    //     for (SavingAccount savingAccount : accounts) {
    //         if (savingAccount.getAccountNum().equals(accountNum)) {
    //             this.getAccounts().remove(savingAccount);
    //             return this.getAccounts();
    //         }
    //     }
    //     BinaryFileService.writeFile("C:/funixjava/Asm4/src/main/resources/account.dat", this.getAccounts());
    //     return this.getAccounts();
    // }

    // List<SavingAccount> addAccount(SavingAccount newAccount) {
    //     if (this.isExistedAccount(newAccount.getAccountNum())) {
    //         System.out.println("tk da ton tai");
    //         return this.getAccounts();
    //     }
    //     this.getAccounts().add(newAccount);
    //     // String AccountInfor = newAccount.getAccountNum() + String.valueOf(newAccount.getBalance());
    //     BinaryFileService.writeFile("C:/funixjava/Asm4/src/main/resources/account.dat", this.getAccounts());
    //     System.out.println("da them thanh cong");
    //     return this.getAccounts();
    // }

    @SuppressWarnings("deprecation")
    String fomatMoney(double money) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        return numberFormat.format(money);
    }

    String cusomerInfor() {
        String customerInformation = String.format("%-30s | %-20s |", this.getID(), this.getName());
        StringBuilder inFor = new StringBuilder(customerInformation);
        if (isPremiumCustomer())
            inFor.append(String.format("%-28s | %43s|", "Premium", this.fomatMoney(getTotalBalance())));
        else
            inFor.append(String.format("%-28s | %43s|", "Normal", this.fomatMoney(getTotalBalance())));
        List<Account> accounts = AccountDao.list();
        List<Account> cusAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getCusID().equals(this.getID())) {
                cusAccounts.add(account);
            }
        }
        for (int i = 0; i < cusAccounts.size(); i++) {
            SavingAccount savingAccount = (SavingAccount) cusAccounts.get(i);
            inFor.append("\n").append(String.format("%-5d%s | %s |", (i+1), " ", savingAccount.accountInfor()));
        }
        return inFor.toString();
    }

    public void transfers(Scanner scanner) {
        System.out.print("nhap so tai khoan gom 6 chu so: ");
        String accountID = scanner.nextLine();
        while (!Bank.checkAccountNum(accountID) || !Bank.isAccountExisted1(this.getAccounts(), accountID)) {
            System.out.print("nhap so tai khoan gom 6 chu so: ");
            accountID = scanner.nextLine();
        }

        System.out.println("nhap so tai khoan nhan (\"no\" de thoat)");
        String receiveAcNum = scanner.nextLine();
        if (receiveAcNum.equals("no")) {
            return;
        }

        Account receiveAccount = Bank.getAccountById(receiveAcNum);
        if (receiveAccount == null) {
            System.out.println("khong ton tai tai khoan");
            return;
        }
        
        String customerReceiveId = receiveAccount.getCusID();
        Customer receiveCustomer = Bank.getCustomerById(CustomerDao.list(), customerReceiveId);
        System.out.printf("chuyen tien den tai khoan %s | %s\n", receiveAcNum, receiveCustomer.getName());
        System.out.print("Nhap so tien chuyen: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (Bank.getAccountById(accountID).getBalance() - amount < 50000) {
            System.out.println("so du khong du");
            return;
        }
        @SuppressWarnings("deprecation")
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        String amountStr = numberFormat.format(amount);
        System.out.printf("Xac nhan tu hien chuyen %s tu tai khoan [%s] den tai khoan [%s] (Y/N)", amountStr, accountID, receiveAcNum);
        String lc = scanner.nextLine();
        if (lc.equals("N")) {
            return;
        }
        SavingAccount transfersAccount = (SavingAccount) Bank.getAccountById(accountID);
        try {
            transfersAccount.transfer(receiveAccount, amount);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Account> getAccounts() {
        List<Account>  accounts = AccountDao.list();
        List<Account> outAccounts = accounts.stream()
                                            .filter(account -> account.getCusID().equals(this.getID()))
                                            .collect(Collectors.toList());
        return outAccounts;
    }

    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNum().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String displayTransactionInformation() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cusomerInfor()+"\n\n");
        List<Account> accounts = getAccounts();
        List<Transaction> transactions = new ArrayList<>();
        for (Account account : accounts) {
            SavingAccount savingAccount = (SavingAccount) account;
            transactions = savingAccount.getTransactions();
            for (Transaction transaction : transactions) {
                stringBuffer.append(transaction.toString()+"\n");
            }
        }
        return stringBuffer.toString();
    }

    public boolean withdraw(Scanner scanner) {
        List<Account> accounts = this.getAccounts();
        if (!accounts.isEmpty()) {
            Account account;
            double amount;
            do {
                System.out.print("nhap so tai khoan: ");
                account = this.getAccountByAccountNumber(accounts, scanner.nextLine());
            } while (account == null);

            do {
                System.out.print("nhap so tien rut: ");
                amount = Double.parseDouble(scanner.nextLine());
            } while (amount <= 0);

            SavingAccount savingAccount = (SavingAccount) account;
            try {
                savingAccount.withDraw(amount);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("khong ton tai tai khoan");
            return false;
        }
        return true;
    }
}
