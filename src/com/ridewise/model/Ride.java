package com.ridewise.model;

import com.ridewise.enums.RideStatus;

public class Ride {

    private int id;
    private Rider rider;
    private Driver driver;
    private long distance;
    private RideStatus status;

    public Ride(int id, Rider rider, Driver driver, long distance, RideStatus status) {
        this.id = id;
        this.rider = rider;
        this.driver = driver;
        this.distance = distance;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", rider=" + rider +
                ", driver=" + driver +
                ", distance=" + distance +
                ", status=" + status +
                '}';
    }
}
