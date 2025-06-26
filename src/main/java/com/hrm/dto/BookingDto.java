package com.hrm.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookingDto {

    @NotNull(message = "user ID is required")
    private Long userId;

    @NotNull(message = "property ID is required")
    private Long propertyId;

    @NotNull(message = "room ID is required")
    private Long roomId;

    @NotNull(message = "from date   is required")
    private LocalDate fromDate;

    @NotNull(message = "to Date is required")
    private LocalDate toDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long hotelId) {
        this.propertyId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}

