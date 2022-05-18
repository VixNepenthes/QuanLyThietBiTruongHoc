package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import java.io.Serializable;

public class ThongKe4Model implements Serializable {
    String maph;
    String loaiph;
    int Tongsolansudung;
    String tentb;
    String loaitb;
    int solansudungnam2017;
    int solansudungnam2018;

    public ThongKe4Model() {
    }

    public ThongKe4Model(String maph, String loaiph, String tentb, String loaitb,int tongsolansudung) {
        this.maph = maph;
        this.loaiph = loaiph;
        Tongsolansudung = tongsolansudung;
        this.tentb = tentb;
        this.loaitb = loaitb;
    }

    public ThongKe4Model(String maph, String loaiph, String tentb, String loaitb,int tongsolansudung, int solansudungnam2017, int solansudungnam2018) {
        this.maph = maph;
        this.loaiph = loaiph;
        this.Tongsolansudung = tongsolansudung;
        this.tentb = tentb;
        this.loaitb = loaitb;
        this.solansudungnam2017 = solansudungnam2017;
        this.solansudungnam2018 = solansudungnam2018;
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

    public int getTongsolansudung() {
        return Tongsolansudung;
    }

    public void setTongsolansudung(int tongsolansudung) {
        Tongsolansudung = tongsolansudung;
    }

    public String getTentb() {
        return tentb;
    }

    public void setTentb(String tentb) {
        this.tentb = tentb;
    }

    public String getLoaitb() {
        return loaitb;
    }

    public void setLoaitb(String loaitb) {
        this.loaitb = loaitb;
    }

    public int getSolansudungnam2017() {
        return solansudungnam2017;
    }

    public void setSolansudungnam2017(int solansudungnam2017) {
        this.solansudungnam2017 = solansudungnam2017;
    }

    public int getSolansudungnam2018() {
        return solansudungnam2018;
    }

    public void setSolansudungnam2018(int solansudungnam2018) {
        this.solansudungnam2018 = solansudungnam2018;
    }
}
