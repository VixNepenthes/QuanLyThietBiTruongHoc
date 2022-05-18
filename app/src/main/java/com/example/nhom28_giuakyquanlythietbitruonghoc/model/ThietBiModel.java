package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import java.io.Serializable;

public class ThietBiModel implements Serializable {
    String MATB;
    String TENTB;
    String XUATXU;
    String MALOAI;

    public ThietBiModel() {
    }

    public ThietBiModel(String MATB, String TENTB, String XUATXU, String MALOAI) {
        this.MATB = MATB;
        this.TENTB = TENTB;
        this.XUATXU = XUATXU;
        this.MALOAI = MALOAI;
    }

    public String getMATB() {
        return MATB;
    }

    public void setMATB(String MATB) {
        this.MATB = MATB;
    }

    public String getTENTB() {
        return TENTB;
    }

    public void setTENTB(String TENTB) {
        this.TENTB = TENTB;
    }

    public String getXUATXU() {
        return XUATXU;
    }

    public void setXUATXU(String XUATXU) {
        this.XUATXU = XUATXU;
    }

    public String getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(String MALOAI) {
        this.MALOAI = MALOAI;
    }
}
