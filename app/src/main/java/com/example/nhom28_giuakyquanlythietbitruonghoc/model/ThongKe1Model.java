package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import java.io.Serializable;

public class ThongKe1Model implements Serializable {
    String MAPHONG;
    String LOAIPHONG;
    String TENTB;
    String TENLOAI;
    int SOLUONG;

    public ThongKe1Model() {
    }

    public ThongKe1Model(String MAPHONG, String LOAIPHONG, String TENTB, String TENLOAI, int SOLUONG) {
        this.MAPHONG = MAPHONG;
        this.LOAIPHONG = LOAIPHONG;
        this.TENTB = TENTB;
        this.TENLOAI = TENLOAI;
        this.SOLUONG = SOLUONG;
    }

    public String getMAPHONG() {
        return MAPHONG;
    }

    public void setMAPHONG(String MAPHONG) {
        this.MAPHONG = MAPHONG;
    }

    public String getLOAIPHONG() {
        return LOAIPHONG;
    }

    public void setLOAIPHONG(String LOAIPHONG) {
        this.LOAIPHONG = LOAIPHONG;
    }

    public String getTENTB() {
        return TENTB;
    }

    public void setTENTB(String TENTB) {
        this.TENTB = TENTB;
    }

    public String getTENLOAI() {
        return TENLOAI;
    }

    public void setTENLOAI(String TENLOAI) {
        this.TENLOAI = TENLOAI;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }
}
