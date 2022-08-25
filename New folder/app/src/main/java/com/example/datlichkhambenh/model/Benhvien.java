package com.example.datlichkhambenh.model;

public class Benhvien {
    private String idBV;
    private String tenBV;
    private String address;

    public Benhvien() {
    }

    public Benhvien(String idBV, String tenBV, String address) {
        this.idBV = idBV;
        this.tenBV = tenBV;
        this.address = address;
    }


    public String getTenBV() {
        return tenBV;
    }

    public void setTenBV(String tenBV) {
        this.tenBV = tenBV;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdBV() {
        return idBV;
    }

    public void setIdBV(String idBV) {
        this.idBV = idBV;
    }
}
