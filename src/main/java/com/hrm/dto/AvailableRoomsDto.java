package com.hrm.dto;

import java.time.LocalDate;
import java.util.Date;

public class AvailableRoomsDto {
    private String roomType;
    private double price;
//    private long availableCount;

    private LocalDate availableDate;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public long getAvailableCount() {
//        return availableCount;
//    }

//    public void setAvailableCount(long availableCount) {
//        this.availableCount = availableCount;
//    }


    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }
}

