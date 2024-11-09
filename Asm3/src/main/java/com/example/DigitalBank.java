package com.example;

import java.util.List;

public class DigitalBank extends Bank{
    public DigitalBank(String _id, List<DigitalCustomer> _cus) {
        super(_id, _cus);
    }

    public DigitalBank() {}
    
    @Override
    public void setCustomers(List<DigitalCustomer> customers) {
        // TODO Auto-generated method stub
        super.setCustomers(customers);
    }

    @Override
    public void setId(String id) {
        // TODO Auto-generated method stub
        super.setId(id);
    }

    @Override
    public
    DigitalCustomer getCustomerById(String cusomerId) {
        // TODO Auto-generated method stub
        return super.getCustomerById(cusomerId);
    }

    @Override
    public List<DigitalCustomer> getCustomers() {
        // TODO Auto-generated method stub
        return super.getCustomers();
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }

    @Override
    public
    boolean isCustomerExisted(String customerId) {
        // TODO Auto-generated method stub
        return super.isCustomerExisted(customerId);
    }

    public List<DigitalCustomer> addCustomer(String customerID, String name) {
        if (isCustomerExisted(customerID)) {
            System.out.println("customer is existed");
        } else {
            DigitalCustomer newCustomer = new DigitalCustomer(name,customerID);
            this.getCustomers().add(newCustomer);
            System.out.println("add sucessul!");
        }
        return this.getCustomers();
    }

    boolean withdraw(String customerID, String accountNumber, double amount) {
        if (isCustomerExisted(customerID)) {
            if (getCustomerById(customerID).isExistedAccount(accountNumber)) {
                Account account = getCustomerById(customerID).getAccountById(accountNumber);
                if (account.getTypeAccount().equals("LOAN")) {
                    LoansAccount loansAccount = new LoansAccount(account);
                    loansAccount.withdraw(amount);
                    account = loansAccount;
                } else {
                    SavingAccount savingAccount = new SavingAccount(account);
                    savingAccount.withdraw(amount);
                    account = savingAccount;
                }
            }
            return true;
        } else {
            System.out.println("don't have any customer with id " + customerID);
            return false;
        }
    }
}
