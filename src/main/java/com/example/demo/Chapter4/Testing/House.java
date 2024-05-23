package com.example.demo.Chapter4.Testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class House {

    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    @Autowired @Qualifier("rooms")
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public House() {
    }

    public void getAllRooms(){
        for(Room myRoom: this.rooms){
            System.out.println(myRoom.getRoomType() + " " + myRoom.getRoomName());
        }
    }
}
