package com.example.nhom28_giuakyquanlythietbitruonghoc.model;

import java.io.Serializable;

public class Top10Model implements Serializable {
    String name;
    int sl;

    public Top10Model() {
    }

    public Top10Model(String name, int sl) {
        this.name = name;
        this.sl = sl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
