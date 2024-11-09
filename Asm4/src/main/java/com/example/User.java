package com.example;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 2L;
    private String ID;
    private String Name;
    
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public User(String iD, String name) {
        ID = iD;
        Name = name;
    }

    public User() {}
}
