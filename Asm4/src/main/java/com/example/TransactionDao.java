package com.example;

import java.io.IOException;
import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = "C:/funixjava/Asm4/src/main/resources/transactions.dat";

    public static void save(List<Transaction> transactions) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }

    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

}
