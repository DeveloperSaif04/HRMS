package com.hrm.entity;

import jakarta.persistence.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private long id;

    @Column(name = "property_name")
    private String name;

    @Column(name="number_of_beds")
    private int numberOfBeds;

    @Column(name="number_of_rooms")
    private int numberOfRooms;

    @Column(name="number_of_bathrooms")
    private int numberOfBathrooms;

    @Column(name="number_of_guests_allowed")
    private int numberOfGuestAllowed;

    @Column(name="price")
    private double price;//added this
    @ManyToOne
    @JoinColumn(name = "area_id")
    private  Area area;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private  City city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private  State state;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
