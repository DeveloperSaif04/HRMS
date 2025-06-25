package com.hrm.dto;

import java.time.LocalDate;


public class AvailableRoomsDto {
    private LocalDate availableDate;
    private double price;
    private String roomType;


    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate=availableDate;
    }
}
