package com.example.datlichkhambenh.model;

public class Doctor {
    String userName, password,level , email, fullName, idKhoa, idBV, timeLV, introduce, status;
    String money;

    public Doctor() {
    }

    public Doctor(String userName, String password, String email, String level, String fullName, String idKhoa, String idBV, String timeLV, String introduce, String money, String status) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.level = level;
        this.fullName = fullName;
        this.idKhoa = idKhoa;
        this.idBV = idBV;
        this.timeLV = timeLV;
        this.introduce = introduce;
        this.money = money;
        this.status = status;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdKhoa() {
        return idKhoa;
    }

    public void setIdKhoa(String idKhoa) {
        this.idKhoa = idKhoa;
    }

    public String getIdBV() {
        return idBV;
    }

    public void setIdBV(String idBV) {
        this.idBV = idBV;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTimeLV() {
        return timeLV;
    }

    public void setTimeLV(String timeLV) {
        this.timeLV = timeLV;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
