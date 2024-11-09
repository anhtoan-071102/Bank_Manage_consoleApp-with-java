package com.example;

public class CustomerIdNotValidException extends Exception{
    public CustomerIdNotValidException(String string) {
        super(string);
    }
}
