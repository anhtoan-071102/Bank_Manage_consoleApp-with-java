package com.example;

public abstract class User {
    private String name;
    private String customerID;
    
    public User(String _name, String _ID) {
        name = _name;
        customerID = _ID;
    }
    public String getName() {
        return name;
    }
    public String getCustomerID() {
        return customerID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}


