package com.example.datlichkhambenh.model;

public class News {
    private String idTT, Tieude, Noidung;
    public News() {
    }
    public News(String idTT, String Tieude, String Noidung) {
        this.idTT = idTT;
        this.Tieude = Tieude;
        this.Noidung = Noidung;
    }

    public String getIdTT() {
        return idTT;
    }

    public void setIdTT(String idTT) {
        this.idTT = idTT;
    }

    public String getTieude() {
        return Tieude;
    }

    public void setTieude(String Tieude) {
        this.Tieude = Tieude;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String Noidung) {
        this.Noidung = Noidung;
    }


}
