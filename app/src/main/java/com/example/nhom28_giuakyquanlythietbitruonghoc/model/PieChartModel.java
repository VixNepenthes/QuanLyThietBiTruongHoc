package com.example.nhom28_giuakyquanlythietbitruonghoc;

public class PieChartModel {
    private String maphong;
    private int tongsl;

    public PieChartModel() {
    }

    public PieChartModel(String matb, int tongsl) {
        this.maphong = matb;
        this.tongsl = tongsl;
    }

    public String getMatb() {
        return maphong;
    }

    public void setMatb(String matb) {
        this.maphong = matb;
    }

    public int getTongsl() {
        return tongsl;
    }

    public void setTongsl(int tongsl) {
        this.tongsl = tongsl;
    }


}
