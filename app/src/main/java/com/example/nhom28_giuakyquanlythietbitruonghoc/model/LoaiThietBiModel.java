package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import java.io.Serializable;

public class LoaiThietBiModel implements Serializable {
    String MALOAI;
    String TENLOAI;

    public LoaiThietBiModel() {
    }

    public LoaiThietBiModel(String MALOAI, String TENLOAI) {
        this.MALOAI = MALOAI;
        this.TENLOAI = TENLOAI;
    }

    public String getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(String MALOAI) {
        this.MALOAI = MALOAI;
    }

    public String getTENLOAI() {
        return TENLOAI;
    }

    public void setTENLOAI(String TENLOAI) {
        this.TENLOAI = TENLOAI;
    }
}
