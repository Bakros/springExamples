package com.example.demo.Chapter4.Testing;

public class Room {
    private String roomType;
    private String roomName;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Room(String roomType, String roomName) {
        this.roomType = roomType;
        this.roomName = roomName;
    }

    public Room() {
    }
}
