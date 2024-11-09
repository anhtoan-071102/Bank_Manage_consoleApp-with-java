package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    Scanner sc = new Scanner(System.in);
    private String ID;

    public Bank() {}

    private List<Customer> customers = new ArrayList<>();

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public boolean tonTaiKhachHang(Customer customer) {
        for (Customer c:customers) {
            if (c.getSoCCCD().equals(customer.getSoCCCD()))
                return true;
        }
        return false;
    }
    public List<Customer> themKhachHang(Customer customer) {
        if (tonTaiKhachHang(customer)) {
            System.out.println("Khach hang da ton tai");
            return customers;
        }
        customers.add(customer);
        System.out.println("Da them khach hang " + customer.getTen() + " vao danh sach.");
        return customers;
    }

    public List<Customer> themTaiKhoanChoKH() {
        String soCCCD = sc.nextLine();
        String soTK = sc.nextLine();
        double soDu = Double.parseDouble(sc.nextLine());
        Acount acount = new Acount(soTK, soDu);
        boolean check = false;
        for (Customer c:getCustomers()) {
            if (c.getSoCCCD().equals(soCCCD)) {
                c.themTaiKhoan(acount);
                return customers;
            }
        }
        return customers;
    }
}
