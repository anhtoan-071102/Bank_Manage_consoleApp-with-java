package com.example;

public class User {
    private String ten;
    private String soCCCD;
    
    public User(String _ten, String _soCCCD) {
        ten = _ten;
        soCCCD = _soCCCD;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }
}
