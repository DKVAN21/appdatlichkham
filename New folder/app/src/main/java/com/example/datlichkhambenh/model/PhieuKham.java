package com.example.datlichkhambenh.model;

public class PhieuKham {
    private String id;
    private String idBs, idBn;
    private String tenBs, tenBn;
    private String status;
    private String mau;
    private String note;
    private String date;
    private String time;
    private String money;
    private String tiensu;
    private String tuoi;
    private String pay;

    public PhieuKham() {
    }

    public PhieuKham(String id, String idBs, String idBn, String tenBs, String tenBn, String status, String note, String date, String time, String mau, String money, String tiensu, String pay, String tuoi) {
        this.id = id;
        this.idBs = idBs;
        this.idBn = idBn;
        this.tenBs = tenBs;
        this.tenBn = tenBn;
        this.status = status;
        this.note = note;
        this.date = date;
        this.time = time;
        this.mau = mau;
        this.money = money;
        this.tiensu = tiensu;
        this.pay = pay;
        this.tuoi = tuoi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdBs() {
        return idBs;
    }

    public void setIdBs(String idBs) {
        this.idBs = idBs;
    }

    public String getIdBn() {
        return idBn;
    }

    public void setIdBn(String idBn) {
        this.idBn = idBn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTenBs() {
        return tenBs;
    }

    public void setTenBs(String tenBs) {
        this.tenBs = tenBs;
    }

    public String getTenBn() {
        return tenBn;
    }

    public void setTenBn(String tenBn) {
        this.tenBn = tenBn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTiensu() {
        return tiensu;
    }

    public void setTiensu(String tiensu) {
        this.tiensu = tiensu;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }
}
