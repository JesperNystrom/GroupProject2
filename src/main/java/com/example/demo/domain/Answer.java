package com.example.demo.domain;

public class Answer {
    private String name;
    private int locationId;

    public Answer(String name, int locationId) {
        this.name = name;
        this.locationId = locationId;
    }

    public String getAnswer() {
        return name;
    }

    public int getLocationId() {
        return locationId;
    }
}
