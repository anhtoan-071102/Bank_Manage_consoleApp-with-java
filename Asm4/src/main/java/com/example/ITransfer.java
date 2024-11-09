package com.example;

import java.io.IOException;

public interface ITransfer {
    void transfer(Account receiveAccount, double amounts) throws IOException;
} 
