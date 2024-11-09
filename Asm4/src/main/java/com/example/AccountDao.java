package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final static String FILE_PATH = "C:/funixjava/Asm4/src/main/resources/account.dat";

    public static void save(List<Account> accounts) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }

    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    public static void update(Account editAccount) throws IOException {
        List<Account> accounts = list();
        boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNum().equals(editAccount.getAccountNum()));

        List<Account> updatedAccounts;
        if (!hasExist) {
            updatedAccounts = new ArrayList<>();
            updatedAccounts.add(editAccount);
        } else {
            updatedAccounts = new ArrayList<>();

            for (Account account2 : accounts) {
                if (account2.getAccountNum().equals(editAccount.getA9999ccountNum())) {
                    updatedAccounts.add(editAccount);
                } else {
                    updatedAccounts.add(account2);
                }
            }
        }
        save(updatedAccounts);
    }
}
