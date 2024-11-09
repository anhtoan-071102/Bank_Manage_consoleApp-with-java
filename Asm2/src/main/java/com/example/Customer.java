package com.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Customer extends User{
    public Customer(String _ten, String _soCCCD) {
        super(_ten, _soCCCD);
    }

    private ArrayList<Acount> acounts = new ArrayList<>();

    boolean laKhachCaoCap() {
        for (Acount a:acounts) {
            if (a.laTkCaoCap())
                return true;
        }
        return false;
    }

    public ArrayList<Acount> getAcounts() {
        return acounts;
    }

    public void setAcounts(ArrayList<Acount> acounts) {
        this.acounts = acounts;
    }

    public void setSoCCCD(String _soCCCD) throws IllegalArgumentException {
        if (Asm02.kiemTraMaCCCD(_soCCCD)) {
            this.setSoCCCD(_soCCCD);
        } else {
            throw new IllegalArgumentException("So CCCD khon hop le");
        }
    }

    ArrayList<Acount> themTaiKhoan(Acount a) {
        for (Acount acount:acounts) {
            if (acount.getSoTaikhoan().equals(a.getSoTaikhoan())) {
                System.out.println("Tai khoan " + a.getSoTaikhoan() + " da ton tai");
                return acounts;
            }
        }
        acounts.add(a);
        return acounts;
    }

    ArrayList<Acount> xoaTaiKhoan(Acount a) {
        if (acounts.contains(a)) {
            System.out.println("ban muon xoa tai khoan " + a.getSoTaikhoan() + " ?    Yes/No");
            Scanner sc = new Scanner(System.in);
            String luachon = sc.nextLine();
            while (!luachon.equals("Yes") || !luachon.equals("No")) {
                System.out.println("Nhap sai moi nhap (Yes) hoac (No)");
                luachon = sc.nextLine();
            }
            if (luachon.equals("Yes")) {
                acounts.remove(a);
                System.out.println("da xoa thanh cong");
            }
            return acounts;
        } else {
            System.out.println("Tai khoan khong ton tai, vui long kiem tra lai");
        }
        return acounts;
    }

    String tongSoDu() {
        double tong = 0;
        for (Acount acount:acounts) {
            tong += acount.getSoDu();
        }
        Locale Vietnam = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(Vietnam);
        String tongSoDu = nf.format(tong);
        return tongSoDu;
    }

    String thongTinKhachHang() {
        String TTTaiKhoan = String.format("%-30s | %10s |", this.getSoCCCD(), this.getTen());
        StringBuilder thongTin = new StringBuilder(TTTaiKhoan);
        if (laKhachCaoCap())
            thongTin.append(String.format("%10s | %28s", "Premium", tongSoDu()));
        else
            thongTin.append(String.format("%10s | %28s", "Normal", tongSoDu()));
        for (int i = 0; i < acounts.size(); i++) {
            thongTin.append("\n").append(String.format("%-5d%s", (i+1), acounts.get(i).toString()));
        }
        return thongTin.toString();
    }

}
