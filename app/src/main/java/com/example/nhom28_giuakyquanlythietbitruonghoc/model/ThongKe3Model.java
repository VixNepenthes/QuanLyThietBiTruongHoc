package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import java.io.Serializable;

public class ThongKe3Model implements Serializable {
    String maph;
    String loaiph;
    String tb1;
    String tb2;

    public ThongKe3Model(String maph, String loaiph, String tb1, String tb2) {
        this.maph = maph;
        this.loaiph = loaiph;
        this.tb1 = tb1;
        this.tb2 = tb2;
    }

    public ThongKe3Model() {
    }

    public String getMaph() {
        return maph;
    }

    public void setMaph(String maph) {
        this.maph = maph;
    }

    public String getLoaiph() {
        return loaiph;
    }

    public void setLoaiph(String loaiph) {
        this.loaiph = loaiph;
    }

    public String getTb1() {
        return tb1;
    }

    public void setTb1(String tb1) {
        this.tb1 = tb1;
    }

    public String getTb2() {
        return tb2;
    }

    public void setTb2(String tb2) {
        this.tb2 = tb2;
    }
}
