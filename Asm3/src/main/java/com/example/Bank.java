package com.example;

import java.beans.Customizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Bank {
    private String id;
    private List<DigitalCustomer> customers;
    Scanner sc =  new Scanner(System.in);


    
    public void setId(String id) {
        this.id = id;
    }

    public void setCustomers(List<DigitalCustomer> customers) {
        this.customers = customers;
    }

    public String getId() {
        return id;
    }

    public List<DigitalCustomer> getCustomers() {
        return customers;
    }

    public Bank(String _id, List<DigitalCustomer> _cus) {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = null;
    }

    public Bank() {}

    

    boolean isCustomerExisted(String customerId) {
        if (this.getCustomers().isEmpty()) {
            return false;
        }
        for (DigitalCustomer customer: customers) {
            if (customer.getCustomerID().equals(customerId)) {
                return true;
            }
        }
        return false;
    }
    
    DigitalCustomer getCustomerById (String cusomerId) {
        DigitalCustomer customer2 = null;
        for (DigitalCustomer customer : customers) {
            if (customer.getCustomerID().equals(cusomerId)) {
                customer2 = customer;
                break;
            }
        }
        return customer2;
    }

    List<DigitalCustomer> addCustomer(DigitalCustomer newCustomer) {
        if (isCustomerExisted(newCustomer.getCustomerID())) {
            System.out.println("customer is existed");
        } else {
            customers.add(newCustomer);
        }
        return customers;
    }

    List<DigitalCustomer> deleteCustomer(String customerId) {
        if (isCustomerExisted(customerId)) {
            System.out.println("do you want to detele customer" + customerId + " yes/no");
            String in = sc.nextLine();
            if (in.equals("yes")) {
                customers.remove(getCustomerById(customerId));
                System.out.println("deleted!");
            } else {
                System.out.println("good bye!");
            }
        } else {
            System.out.println("don't find any customer with id " + customerId);
        }
        return customers;
    }
}
