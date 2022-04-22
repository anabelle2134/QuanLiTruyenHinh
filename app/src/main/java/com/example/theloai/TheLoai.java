package com.example.theloai;

public class TheLoai {
    public static final String tenBang = "THELOAI";
    public static final String cotmaTheLoai = "maTheLoai";
    public static final String cotTenTheLoai = "tenTheLoai";
    public static final String cotMoTa = "moTa";
    int maTheLoai;
    String tenTheLoai;
    String moTa;

    public TheLoai(String tenTheLoai, String moTa) {
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
