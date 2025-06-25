package com.hrm.dto;

import jakarta.persistence.Column;

import java.util.List;

public class PropertyDto {

   private String name;
   private int  numberOfBeds;
   private int numberOfRooms;
   private int  numberOfBathrooms;
   private int numberOfGuestAllowed;
   private String area;
   private String city;
   private String state;
   private List<RoomsDto> roomsDtoList;

    public List<RoomsDto> getRoomsDtoList() {
        return roomsDtoList;
    }

    public void setRoomsDtoList(List<RoomsDto> roomsDtoList) {
        this.roomsDtoList = roomsDtoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfGuestAllowed() {
        return numberOfGuestAllowed;
    }

    public void setNumberOfGuestAllowed(int numberOfGuestAllowed) {
        this.numberOfGuestAllowed = numberOfGuestAllowed;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area){
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
