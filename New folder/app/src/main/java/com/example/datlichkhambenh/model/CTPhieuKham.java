package com.example.datlichkhambenh.model;

public class CTPhieuKham {
    private String id;
    private String idBs, idBn;
    private String chidinh;
    private String benh;
    private String dando;
    private String toathuoc;
    private String URL;
    private String date;
    private String time;

    public CTPhieuKham() {
    }

    public CTPhieuKham(String id, String idBs, String idBn, String chidinh, String benh, String dando, String toathuoc, String uRL, String date, String time) {
        this.id = id;
        this.idBs = idBs;
        this.idBn = idBn;
        this.date = date;
        this.time = time;
        this.toathuoc = toathuoc;
        this.benh = benh;
        this.chidinh = chidinh;
        this.URL = uRL;
        this.dando = dando;
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

    public String getToathuoc() {
        return toathuoc;
    }

    public void setToathuoc(String toathuoc) {
        this.toathuoc = toathuoc;
    }

    public String getBenh() {
        return benh;
    }

    public void setBenh(String benh) {
        this.benh = benh;
    }

    public String getChidinh() {
        return chidinh;
    }

    public void setChidinh(String chidinh) {
        this.chidinh = chidinh;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDando() {
        return dando;
    }

    public void setDando(String dando) {
        this.dando = dando;
    }

}
