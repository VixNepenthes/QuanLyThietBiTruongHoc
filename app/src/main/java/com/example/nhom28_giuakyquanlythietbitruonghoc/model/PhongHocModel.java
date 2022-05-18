package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import java.io.Serializable;

public class PhongHocModel implements Serializable {
    String MAPHONG;
    String LOAIPHONG;
    int TANG;

    public PhongHocModel() {
    }

    public PhongHocModel(String MAPHONG, String LOAIPHONG, int TANG) {
        this.MAPHONG = MAPHONG;
        this.LOAIPHONG = LOAIPHONG;
        this.TANG = TANG;
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

    public int getTANG() {
        return TANG;
    }

    public void setTANG(int TANG) {
        this.TANG = TANG;
    }
}
