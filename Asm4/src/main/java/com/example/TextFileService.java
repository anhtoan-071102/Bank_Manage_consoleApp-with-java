package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";
    static List<List<String>> readFile(String fileName) {
        List<List<String>> out = new ArrayList<>();
        List<String> customerList;
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                boolean isFirstLine = true;
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    customerList = new ArrayList<>();
                    String[] cus = line.split(COMMA_DELIMITER);
                    customerList.add(cus[0]);
                    customerList.add(cus[1]);
                    out.add(customerList);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
