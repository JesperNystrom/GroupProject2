package com.example.demo.domain;

public class Answer {
    private String name;
    private int locationId;
    private int id;

    public Answer(String name, int locationId, int id) {
        this.name = name;
        this.locationId = locationId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getId() {
        return id;
    }
}
