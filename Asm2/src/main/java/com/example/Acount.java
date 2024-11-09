package com.example;

import java.text.NumberFormat;
import java.util.Locale;

public class Acount {
    private String soTaikhoan;
    private double soDu;

    public Acount(String _soTaikhoan, double _soDu) {
        this.soTaikhoan = _soTaikhoan;
        this.soDu = _soDu;
    }

    public String getSoTaikhoan() {
        return soTaikhoan;
    }

    public void setSoTaikhoan(String soTaikhoan) {
        this.soTaikhoan = soTaikhoan;
    }

    public double getSoDu() {
        return soDu;
    }

    public void setSoDu(double soDu) {
        this.soDu = soDu;
    }

    public boolean laTkCaoCap() {
        if (this.soDu >= 10000000)
            return true;
        return false;
    }

    @Override
    public String toString() {
        Locale Vietnam = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(Vietnam);
        String soDu1 = nf.format(soDu);
        String TTTK = String.format("%-25s | %25s%30s", soTaikhoan, " ", soDu1);
        return TTTK;
    }
}
