package com.example;

public class IReport {
    private StringBuffer stBuffer = new StringBuffer();
    private final String divider = "+----------+-------------------------+----------+\n";
    private final String title = String.format("%30s%n" ,"BIEN LAI GIAO DICH SAVINGS");
    private String DateTime;
    private final String bankId = String.format("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2022");
    private String accountNum;
    private String moneyOut;
    private String receiveAcNum;
    private String newBalance;
    private final String feeAndVat = String.format("PHI + VAT: %27s%n", "0đ");

    public IReport() {}

    


    public StringBuffer getStBuffer() {
        return stBuffer;
    }




    public void setStBuffer(StringBuffer stBuffer) {
        this.stBuffer = stBuffer;
    }




    public String getDivider() {
        return divider;
    }




    public String getTitle() {
        return title;
    }




    public String getDateTime() {
        return DateTime;
    }




    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }




    public String getBankId() {
        return bankId;
    }




    public String getAccountNum() {
        return accountNum;
    }




    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }




    public String getMoneyOut() {
        return moneyOut;
    }




    public void setMoneyOut(String moneyOut) {
        this.moneyOut = moneyOut;
    }




    public String getReceiveAcNum() {
        return receiveAcNum;
    }




    public void setReceiveAcNum(String receiveAcNum) {
        this.receiveAcNum = receiveAcNum;
    }




    public String getNewBalance() {
        return newBalance;
    }




    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }




    public String getFeeAndVat() {
        return feeAndVat;
    }




    public String log(double amount, TransactionType type, Account receiveAccount) {
        // StringBuffer stBuffer = new StringBuffer();
        // String divider = "+----------+-------------------------+----------+\n";
        // String title = String.format("%30s%n" ,"BIEN LAI GIAO DICH SAVINGS");
        // String DateTime = String.format("NGAY G/D: %28s%n", transaction.getDateTime());
        // String bankId = String.format("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2022");
        // String accountNum = String.format("SO TK: %31s%n", transaction.getAccountNum());
        // String moneyOut;
        // if (type.getType().equals("withdraw")) {
        //     moneyOut = String.format("SO TIEN RUT: %29s%n", transaction.getAmString());
        // } else {
        //     moneyOut = String.format("SO TIEN CHUYEN: %29s%n", transaction.getAmString());
        // }
        // String receiveAcNum = receiveAccount.getAccountNum();
        // String newBalance = String.format("SO DU: %31s%n", SavingAccount.getAccountByAccountNumber());
        // String feeAndVat = String.format("PHI + VAT: %27s%n", "0đ");

        stBuffer.append(divider);
        stBuffer.append(title);
        stBuffer.append(DateTime);
        stBuffer.append(bankId);
        stBuffer.append(accountNum);
        if (receiveAccount != null && !type.getType().equals("withdraw")) {
            stBuffer.append(receiveAcNum);
        }
        stBuffer.append(moneyOut);
        stBuffer.append(newBalance);
        stBuffer.append(feeAndVat);
        stBuffer.append(divider);
        
        return stBuffer.toString();
    }
}
