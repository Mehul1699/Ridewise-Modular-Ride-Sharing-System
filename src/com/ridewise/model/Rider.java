package com.ridewise.model;

public class Rider extends Person {

    private String location;

    public Rider(int id, String name, String location) {
        super(id, name);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Rider{" +
                super.toString() +
                ", location='" + location + '\'' +
                '}';
    }
}
