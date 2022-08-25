package com.example.datlichkhambenh.model;

public class Toathuoc {
    private String id, tenTh, sL, ngay;
    public Toathuoc() {
    }
    public Toathuoc(String id, String tenTh, String sL, String ngay) {
        this.id = id;
        this.tenTh = tenTh;
        this.sL = sL;
        this.ngay = ngay;
    }

    public String getsL() {
        return sL;
    }

    public void setsL(String sL) {
        this.sL = sL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTh() {
        return tenTh;
    }

    public void setTenTh(String tenTh) {
        this.tenTh = tenTh;
    }


    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

}
