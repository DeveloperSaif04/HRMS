package com.hrm.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AvailableRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availableRooms_id")
    private long id;

    @Column(name = "available_datetime")
    private LocalDateTime availableDateTime;

    @Column(name = "available_count")
    private long availableCount;

    @Column(name = "price")
    private  double price;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms rooms;

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getAvailableDateTime() {
        return availableDateTime;
    }

    public void setAvailableDateTime(LocalDateTime availableDateTime) {
        this.availableDateTime = availableDateTime;
    }

    public long getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(long availableCount) {
        this.availableCount = availableCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
