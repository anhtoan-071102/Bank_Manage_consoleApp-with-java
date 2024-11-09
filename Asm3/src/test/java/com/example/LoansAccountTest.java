package com.example;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoansAccountTest {
    LoansAccount  loansAccount =  new LoansAccount("123456", 15000000);
    @Test
    public void testIsAccepted() {
        double amount = 200000;
        assertTrue(loansAccount.isAccepted(amount));
    }

    @Test
    public void testIsPrimiumAccount() {
        assertTrue(loansAccount.isPrimiumAccount());
    }

    @Test
    public void testWithdraw() {
        double amount = 100000;
        assertTrue(loansAccount.withdraw(amount));
    }
}
