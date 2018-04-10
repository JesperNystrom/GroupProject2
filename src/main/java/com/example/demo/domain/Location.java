package com.example.demo.domain;

public class Location {
    private final int id;
    private final String name;
    private final String coordinates;

    public Location(int id, String name, String coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCoordinates() {
        return coordinates;
    }
}
