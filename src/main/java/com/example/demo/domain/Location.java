package com.example.demo.domain;

public class Location {
    private final int id;
    private final String name;
    private final String image;
    private final String question;

    public Location(int id, String name, String image, String question) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getQuestion() {
        return question;
    }
}
