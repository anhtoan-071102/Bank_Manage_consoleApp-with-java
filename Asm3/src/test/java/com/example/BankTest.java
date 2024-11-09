package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BankTest {
    DigitalBank digitalBank = new DigitalBank();
    List<DigitalCustomer> listCustomer = new ArrayList<>();
    @Test
    public void testGetCustomerById() {
        DigitalCustomer sCustomer = new DigitalCustomer("Nguyen Van Nam", "001215000001");
        listCustomer.add(sCustomer);
        digitalBank.setCustomers(listCustomer);
        String cusID = "001215000001";
        DigitalCustomer digitalCustomer =  digitalBank.getCustomerById(cusID);
        assertEquals(sCustomer, digitalCustomer); 
    }

    @Test
    public void testIsCustomerExisted() {
        DigitalCustomer sCustomer = new DigitalCustomer("Nguyen Van Nam", "001215000001");
        listCustomer.add(sCustomer);
        digitalBank.setCustomers(listCustomer);
        String cusID = "001215000001";
        assertTrue(digitalBank.isCustomerExisted(cusID));
    }
}
