package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import java.io.Serializable;

public class ChiTietSuDungModel implements Serializable {
    String MAPHONG;
    String MATB;
    String NGAYSUDUNG;
    int SOLUONG;

    public ChiTietSuDungModel() {
    }

    public ChiTietSuDungModel(String MAPHONG, String MATB, String NGAYSUDUNG, int SOLUONG) {
        this.MAPHONG = MAPHONG;
        this.MATB = MATB;
        this.NGAYSUDUNG = NGAYSUDUNG;
        this.SOLUONG = SOLUONG;
    }

    public String getMAPHONG() {
        return MAPHONG;
    }

    public void setMAPHONG(String MAPHONG) {
        this.MAPHONG = MAPHONG;
    }

    public String getMATB() {
        return MATB;
    }

    public void setMATB(String MATB) {
        this.MATB = MATB;
    }

    public String getNGAYSUDUNG() {
        return NGAYSUDUNG;
    }

    public void setNGAYSUDUNG(String NGAYSUDUNG) {
        this.NGAYSUDUNG = NGAYSUDUNG;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }
}
