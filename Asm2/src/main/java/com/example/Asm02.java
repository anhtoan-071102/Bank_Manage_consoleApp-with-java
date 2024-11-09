package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Asm02 {
    private static final Bank nganHang = new Bank();
    static Scanner sc = new Scanner(System.in);

    static boolean thoat = false;

    public static boolean checkStringInList(String a, List<String> stringList) {
        return stringList.stream().anyMatch(s -> s.equalsIgnoreCase(a));
    }

    public static boolean kiemTraMaCCCD(String s) {
        if (s.length() != 12)
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    public static Bank themKhachHang() {
        System.out.println("Nhap so CCCD: ");
        String soCCCD = sc.nextLine();
        while (!kiemTraMaCCCD(soCCCD)) {
            System.out.println("So CCCD khong hop le.\nVui long nhap lai hoac nhap No de thoat.");
            soCCCD = sc.nextLine();
            if (soCCCD.equals("No")) {
                thoat = true;
                return nganHang;
            }
        }

        System.out.println("Moi nhap ten: ");
        String ten = sc.nextLine();
        Customer customer = new Customer(ten, soCCCD);
        nganHang.setCustomers(nganHang.themKhachHang(customer));
        return nganHang;
    }

    public static Bank themTaiKhoanChoKhach() {
        System.out.println("Nhap so CCCD");
        String soCCCD = sc.nextLine();
        for (Customer c: nganHang.getCustomers()) {
            if (c.getSoCCCD().equals(soCCCD)) {
                System.out.println("Nhap ma so TK gom 6 chu so ");
                String soTK = sc.nextLine();
                boolean kTra = false, kTra1 = false;
                while (!kTra && !kTra1) {
                    if (soTK.length() == 6) {
                        kTra1 = true;
                        boolean check = true;
                        for (int i = 0; i < soTK.length(); i++) {
                            if (!Character.isDigit(soTK.charAt(i))) {
                                System.out.println("soTK chi bao gom cac chu so. Vui long nhap lai");
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            kTra = true;
                        } else {
                            soTK = sc.nextLine();
                        }
                    } else {
                        System.out.println("Do dai khong hop le (6 ky tu). Vui long nhap lai soTK");
                        soTK = sc.nextLine();
                    }
                }
                System.out.println("Nhap so du >=50000");
                double soDu = Double.parseDouble(sc.nextLine());
                while (soDu < 0 || soDu < 50000) {
                    System.out.println("So du khong hop le. Vui long nhap lai");
                    soDu = Double.parseDouble(sc.nextLine());
                }
                Acount acount = new Acount(soTK, soDu);
                c.themTaiKhoan(acount);
                break;
            } else {
                System.out.println("Khong co khach hang voi soCCCD la: " + soCCCD);
                break;
            }
        }
        return nganHang;
    }

    public static String hienThiDanhSachKhachHang(Bank bank) {
        String s = "";
        for (Customer c: bank.getCustomers()) {
            s = s + "\n" + c.thongTinKhachHang();
        }
        return s;
    }

    public static String timTheoCCCD(String soCCCD) {
        for (Customer c:nganHang.getCustomers()) {
            if (c.getSoCCCD().equals(soCCCD)) {
                return "Tim thay: \n" + c.thongTinKhachHang();
            }
        }
        return "khong ton tai khach hang voi so CCCD: " + soCCCD;
    }

    public static String timTheoTenKhach() {
        String ten = sc.nextLine();
        String tenCanTim = ten.replaceAll("\\s+", "");
        for (Customer c:nganHang.getCustomers()) {
            if (c.getTen().replaceAll("\\s", "").equalsIgnoreCase(tenCanTim)) {
                return "tim thay: \n" + c.thongTinKhachHang();
            }
        }
        return "Khong ton tai khach hang co ten la: " + ten;
    }
    static void Menu() {
        while (!thoat) {
            System.out.println(
                "+-------------+-----------------------+-------------+\n" +
                "|   NGAN HANG SO    |        FX22602@v2.0.0         |\n" +
                "+-------------+-----------------------+-------------+\n" +
                "|   1. Them khach hang                              |\n" +
                "|   2. Them tai khoan cho khach hang                |\n" +
                "|   3. Hien thi danh sach khach hang                |\n" +
                "|   4. Tim theo CCCD                                |\n" +
                "|   5. Tim theo ten khach hang                      |\n" +
                "|   0. Thoat                                        |\n" +
                "+-------------+-----------------------+-------------+"
        );
            System.out.println("Nhap lua chon: ");
            String luaChon = sc.nextLine();
            List<String> cacLuaChon = Arrays.asList("0", "1", "2", "3", "4", "5");
            while (!checkStringInList(luaChon, cacLuaChon)) {
                System.out.println("KHong hop le." +
                        "\nMoi nhap lai:");
                luaChon = sc.nextLine();
            }
            switch (luaChon) {
                case "1":
                    themKhachHang();
                    break;
                case "2":
                    themTaiKhoanChoKhach();
                    break;
                case "3":
                    System.out.println(hienThiDanhSachKhachHang(nganHang));
                    break;
                case "4":
                    System.out.println("Nhap so CCCD can tim: ");
                    String soCCCD = sc.nextLine();
                    System.out.println(timTheoCCCD(soCCCD));
                    break;
                case "5":
                    System.out.println("nhap ten khach hang can tim: ");
                    System.out.println(timTheoTenKhach());
                    break;
                default:
                    thoat = true;
                    System.out.println("Good by");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        nganHang.setCustomers(customers);
        Menu();
    }
}
