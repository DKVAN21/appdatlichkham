package com.example.datlichkhambenh.model;

public class Users {
    private String userName, fullName, password, email, level, age, birthday, phone, mau, tiensu;

    public Users() {
    }

    public Users(String userName, String fullName, String password, String email, String level, String age, String birthday, String phone, String mau, String tiensu) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.level = level;
        this.age = age;
        this.birthday = birthday;
        this.phone = phone;
        this.mau = mau;
        this.tiensu = tiensu;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getTiensu() {
        return tiensu;
    }

    public void setTiensu(String tiensu) {
        this.tiensu = tiensu;
    }
}
