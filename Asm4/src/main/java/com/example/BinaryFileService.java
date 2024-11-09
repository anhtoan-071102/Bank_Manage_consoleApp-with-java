package com.example;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {
    public static <T> void writeFile(String filePath, List<T> objects) {
        try (ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (T object : objects) {
                file.writeObject(object);
            }
        } catch (IOException e) {
            // System.err.println("loi khi doc file: " + e.getMessage());
        }
    }

    public static <T> List<T> readFile(String fileName) {
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream file = new ObjectInputStream(new FileInputStream(fileName))) {
            boolean eof = false;
            while (!eof) {
                try {
                    @SuppressWarnings("unchecked")
                    T object = (T) file.readObject();
                    objects.add(object);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // System.err.println("loi khi doc file: " + e.getMessage());
        }
        return objects;
    }
}
