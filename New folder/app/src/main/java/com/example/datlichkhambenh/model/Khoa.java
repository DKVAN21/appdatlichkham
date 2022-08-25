package com.example.datlichkhambenh.model;

public class Khoa {
    private String tenKhoa;
    private String idBV;
    private String idKhoa;

    public Khoa() {
    }

    public Khoa( String tenKhoa, String idBV, String idKhoa) {
        this.tenKhoa = tenKhoa;
        this.idBV = idBV;
        this.idKhoa = idKhoa;
    }


    public String getTenKhoa() { return tenKhoa; }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getIdBV() {
        return idBV;
    }

    public void setIdBV(String idBV) {
        this.idBV= idBV;
    }

    public String getIdKhoa() {
        return idKhoa;
    }

    public void setIdKhoa(String idKhoa) {
        this.idKhoa= idKhoa;
    }
}
