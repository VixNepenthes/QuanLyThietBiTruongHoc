package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

public class BaoCaoModel {
    private int STT;
    private String LoaiPhong;
    private int Tang;
    private String NgaySD;
    private int SoLuong;

    public BaoCaoModel() {
    }

    public BaoCaoModel(int STT, String loaiPhong, int tang, String ngaySD, int soLuong) {
        this.STT = STT;
        LoaiPhong = loaiPhong;
        Tang = tang;
        NgaySD = ngaySD;
        SoLuong = soLuong;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        LoaiPhong = loaiPhong;
    }

    public int getTang() {
        return Tang;
    }

    public void setTang(int tang) {
        Tang = tang;
    }

    public String getNgaySD() {
        return NgaySD;
    }

    public void setNgaySD(String ngaySD) {
        NgaySD = ngaySD;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
