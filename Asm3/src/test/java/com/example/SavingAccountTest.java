package com.example;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SavingAccountTest {
    SavingAccount savingAccount = new SavingAccount("234567", 15000000);
    @Test
    public void testIsAccepted() {
        double amount = 100000;
        assertTrue(savingAccount.isAccepted(amount));
    }

    @Test
    public void testWithdraw() {
        double  amount = 200000;
        assertTrue(savingAccount.withdraw(amount));
    }

    @Test
    public void testIsPrimiumAccount() {
        assertTrue(savingAccount.isPrimiumAccount());
    }
}
