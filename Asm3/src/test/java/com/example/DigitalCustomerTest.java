package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class DigitalCustomerTest {

    DigitalCustomer digitalCustomer = new DigitalCustomer("Nam", "001215000001");
    ArrayList<Account> accounts = new ArrayList<>();
    LoansAccount loansAccount = new LoansAccount("123456", 15000000);
    SavingAccount savingAccount = new SavingAccount("234567", 25000000);
    

    @Test
    public void testGetAccountById() {
        accounts.add(loansAccount);
        accounts.add(savingAccount);
        digitalCustomer.setAcounts(accounts);

        Account account1 = digitalCustomer.getAccountById("123456");
        assertEquals(loansAccount, account1);
    }

    @Test
    public void testIsExistedAccount() {
        String accountNum = "123456";
        assertTrue(digitalCustomer.isExistedAccount(accountNum));
    }

    @Test
    public void testIsPremiumCustomer() {
        assertTrue(digitalCustomer.isPremiumCustomer());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testTotalAmount() {
        double amount =  digitalCustomer.totalAmount();
        assertEquals(40000000, amount);
    }
}
