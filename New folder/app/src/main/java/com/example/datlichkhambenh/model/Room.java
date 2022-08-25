package com.example.datlichkhambenh.model;

public class Room {
    private String idRoom;
    private String tenRoom;
    private String status;

    public Room() {
    }

    public Room(String idRoom, String tenRoom, String status) {
        this.idRoom = idRoom;
        this.tenRoom = tenRoom;
        this.status = status;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getTenRoom() {
        return tenRoom;
    }

    public void setTenRoom(String tenRoom) {
        this.tenRoom = tenRoom;
    }

    public String getStatus() {
        return getStatus();
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
