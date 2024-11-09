package org.example.Asm01;

import java.io.*;
import java.util.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Asm01 {
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
    
    static HashMap<String, String> docTenvaMaTinhTuFile(String tenFile) {
        HashMap<String,String> tinhThanh = new HashMap<>();
        try {
            BufferedReader danhSachMaTinh = new BufferedReader(new FileReader(tenFile));
            String vanBan = danhSachMaTinh.readLine();
            while (vanBan != null) {
                if (vanBan.equals("Tinh,maTinh")) {
                    vanBan = danhSachMaTinh.readLine();
                }
                String [] mangVanBan = vanBan.split(",");
                tinhThanh.put(mangVanBan[1], mangVanBan[0]);
                vanBan = danhSachMaTinh.readLine();
            }
        } catch (Exception e) {
            System.out.println("!Loi he thong! Khong tim thay du lieu ve tinh thanh");
        }
        return tinhThanh;
    }
    
    static void Menu2(String maTinh, int maGioiTinh, int maNamSinh, String ngauNhien ) {
        label:
        while (true) {
            System.out.println("""
                    +-------------+-----------------------+-------------+
                    |   NGAN HANG SO    |        FX22602@v1.0.0         |
                    +-------------+-----------------------+-------------+
                    |   1. Kiem tra noi sinh                            |
                    |   2. Kiem tra nam sinh, gioi tinh                 |
                    |   3. Kiem tra so ngau nhien                       |
                    |   0. Thoat                                        |
                    +-------------+-----------------------+-------------+""");

            Scanner sc = new Scanner(System.in);
            System.out.println("Moi ban nhap lua chon: ");
            String luaChon = sc.nextLine();
            List<String> stringList = Arrays.asList("0", "1", "2", "3");
            while (!checkStringInList(luaChon, stringList)) {
                System.out.println("KHong hop le." +
                        "\nMoi nhap lai:");
                luaChon = sc.nextLine();
            }
//
            switch (luaChon) {
                case "0":
                    System.out.println("Good bye");
                    break label;
                case "1":
                    HashMap<String, String> tenVaMaTinh = docTenvaMaTinhTuFile("C:/Users/ADMIN/Desktop/Asm1/src/main/resources/Province.csv");
                    System.out.println("Chuc nang: " + luaChon + ". Kiem tra noi sinh\n+----------------------------------------------------------------+");
                    String noiSinh = tenVaMaTinh.get(maTinh);
                    System.out.println("Noi sinh: " + noiSinh);
                    break;
                case "2":
                    System.out.println("Chuc nang: " + luaChon + ". Kiem tra nam sinh, gioi tinh\n+----------------------------------------------------------------+");
                    String gioiTinh = maGioiTinh % 2 == 0 ? "Nam" : "Nu";

                    int namSinh = 1900 + (maGioiTinh / 2) * 100 + maNamSinh;
                    System.out.println("Gioi tinh: " + gioiTinh + " | Nam sinh: " + namSinh);
                    break;
                default:
                    System.out.println("Chuc nang: " + luaChon + ". Kiem tra so ngau nhien\n+----------------------------------------------------------------+");
                    System.out.println("So ngau nhien: " + ngauNhien);
                    break;
            }
        }
    }
    
    public static void Menu1() {
        boolean thoat = false;
        while (!thoat) {
            System.out.println("""
                    +-------------+-----------------------+-------------+
                    |   NGAN HANG SO    |        FX22602@v1.0.0         |
                    +-------------+-----------------------+-------------+
                    |   1. Nhap CCCD                                    |
                    |   0. Thoat                                        |
                    +-------------+-----------------------+-------------+""");

            Scanner sc = new Scanner(System.in);
            System.out.println("Moi ban nhap lua chon: ");
            String luaChon = sc.nextLine();
            List<String> stringList = Arrays.asList("0", "1");
            while (!checkStringInList(luaChon, stringList)) {
                System.out.println("Khong hop le." +
                        "\nMoi nhap lai: ");
                luaChon = sc.nextLine();
            }
            int luaChon1 = Integer.parseInt(luaChon);
            if (luaChon1 == 0) {
                System.out.println("Good bye");
                break;
            } else {
                System.out.println("Chuc nang: " + luaChon);
                Random ran = new Random();
                int maBaomat = ran.nextInt(100, 999);
                System.out.printf("Ma bao mat la %d \nNhap ma bao mat: ", maBaomat);
//
                int nhapMaBM = sc.nextInt();
//
                while (nhapMaBM != maBaomat) {
                    System.out.println("Ma bao mat khong chinh xac." +
                            "\nVui long nhap lai: ");
                    nhapMaBM = sc.nextInt();
                }
//
                sc.nextLine();
                System.out.println("Ma bao mat chinh xac." +
                        "\nVui long nhap so CCCD: ");
                String cccdNum = sc.nextLine();
//
                while (!kiemTraMaCCCD(cccdNum)) {
                    System.out.println("So cccd khong hop le. " +
                            "\nVui long nhap lai hoac 'No' de thoat: ");
                    cccdNum = sc.nextLine();
                    if (cccdNum.equals("No")) {
                        thoat = true;
                        break;
                    }
                }
                if (thoat) {
                    break;
                }
                else {
                    String maTinh = cccdNum.substring(0,3);
                    int maGioiTinh = Integer.parseInt(cccdNum.substring(3,4));
                    int maNamSinh = Integer.parseInt(cccdNum.substring(4,6));
                    String ngauNhien = cccdNum.substring(6,12);
                    Menu2(maTinh, maGioiTinh, maNamSinh, ngauNhien);
                }
            }
        }
    }

    public static void main(String[] args) {
        Menu1();
    }
}
