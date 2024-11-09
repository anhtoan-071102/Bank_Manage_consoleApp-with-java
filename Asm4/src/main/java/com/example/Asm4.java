package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Asm4 {
    static Bank bank = new Bank("1238411", "BIDV");

    public static boolean checkStringInList(String a, List<String> stringList) {
        return stringList.stream().anyMatch(s -> s.equalsIgnoreCase(a));
    }

    static void Menu() {
        String cusString;
        boolean out = true;
        while (out) {
            System.out.println("+----------+-----------------------------------------------+----------+");
            System.out.println("| NGAN HANG DIEN TU |           FX22602@v4.0.0                        |");
            System.out.println("1. Xem danh sach khach hang                                           |");
            System.out.println("2. Nhap danh sach khach hang                                          |");
            System.out.println("3. Them tai khoan ATM                                                 |");
            System.out.println("4. Rut tien                                                           |");
            System.out.println("5. Chuyen tien                                                        |");
            System.out.println("6. Lich su giao dich                                                  |");
            System.out.println("0. Thoat                                                              |");
            System.out.println("+----------+-----------------------------------------------+----------+");

            Scanner sc = new Scanner(System.in);
            String in = "";
            System.out.print("enter your choose: ");
            in = sc.nextLine();
            List<String> chooses = Arrays.asList("0", "1", "2", "3", "4", "5", "6");
            while (!checkStringInList(in, chooses)) {
                System.out.println("wrong, enter again");
                in = sc.nextLine();
            }
            switch (in) {
                case "1":
                    System.out.println("CHuc nang 1. Xem thong tin khach hang: ");
                    bank.showCustomers();
                    break;
                case "2":
                    System.out.println("Chuc nang 2. Nhap danh sach khach hang ");
                    System.out.println("nhap duong dan den tep:");
                    String fileName = sc.nextLine();
                    bank.addCustomers(fileName);
                    System.out.println("sucessful!");
                    break;
                case "3":
                    System.out.println("Chuc nang 3. Them tai khoan ATM: ");
                    System.out.println("nhap ma so khach hang: ");
                    cusString = sc.nextLine();
                    bank.addSavingAccount(sc, cusString);
                    System.out.println("sucessful!");
                    break;
                case "4":
                    System.out.println("Chuc nang 4, Rut tien: ");
                    System.out.println("nhap ma khach hang: ");
                    cusString = sc.nextLine();
                    bank.withdraw(sc, cusString);
                    break;
                case "5":
                    System.out.println("Chuc nang 5, Chuyen tien: ");
                    System.out.println("nhap ma so khach hang:");
                    cusString = sc.nextLine();
                    bank.tranfers(sc, cusString);
                    break;
                case "6":
                    System.out.println("nhap ma so cua khach hang:");
                    cusString = sc.nextLine();
                    Customer customer = Bank.getCustomerById(CustomerDao.list(), cusString);
                    if (customer != null) {
                        System.out.println(customer.displayTransactionInformation());
                    } else {
                        System.out.println("khach hang khong ton tai");
                    }
                    break;
                default:
                    out = false;
                    System.out.println("good bye");
                    break;
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, CustomerIdNotValidException {
        Menu();
    }
}