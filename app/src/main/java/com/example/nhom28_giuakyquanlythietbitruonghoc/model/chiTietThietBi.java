package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import android.graphics.Bitmap;

import java.util.Date;

public class chiTietThietBi {
    private int id;
    private String maP;
    private String maTB;
    private String ngay;
    private int sl;
   private byte[] Hinh;


    public chiTietThietBi(int id, String maP, String maTB, String ngay, int sl, byte[] hinh) {
        this.id = id;
        this.maP = maP;
        this.maTB = maTB;
        this.ngay = ngay;
        this.sl = sl;
        Hinh = hinh;
    }


    public chiTietThietBi() {
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public void setMaTB(String maTB) {
        this.maTB = maTB;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMaP() {
        return maP;
    }

    public String getMaTB() {
        return maTB;
    }

    public String getNgay() {
        return ngay;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
