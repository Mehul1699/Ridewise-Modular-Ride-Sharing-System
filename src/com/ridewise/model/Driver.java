package com.ridewise.model;

import com.ridewise.enums.VehicleType;

public class Driver extends Person {

    private String currentLocation;
    private boolean isAvailable;
    private int completedRideCount;
    private VehicleType vehicleType;

    public Driver(int id, String name, String currentLocation,
                  boolean isAvailable, VehicleType vehicleType, int completedRideCount) {
        super(id, name);
        this.currentLocation = currentLocation;
        this.isAvailable = isAvailable;
        this.vehicleType = vehicleType;
        this.completedRideCount = completedRideCount;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getCompletedRideCount() {
        return completedRideCount;
    }

    public void setCompletedRideCount(int completedRideCount) {
        this.completedRideCount = completedRideCount;
    }

    @Override
    public String toString() {
        return "Driver{" +
                super.toString() +
                ", currentLocation='" + currentLocation + '\'' +
                ", isAvailable=" + isAvailable +
                ", completedRideCount=" + completedRideCount +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
