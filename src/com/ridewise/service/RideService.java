package com.ridewise.service;

import com.ridewise.enums.RideStatus;
import com.ridewise.exception.RideNotFoundException;
import com.ridewise.interfaces.FareStrategy;
import com.ridewise.interfaces.RideMatchingStrategy;
import com.ridewise.model.Driver;
import com.ridewise.model.FareReceipt;
import com.ridewise.model.Ride;
import com.ridewise.model.Rider;
import com.ridewise.util.IdGenerator;
import com.ridewise.util.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RideService {

    List<Ride> rides = new ArrayList<>();
    private final RideMatchingStrategy rideMatchingStrategy;
    private final FareStrategy fareStrategy;

    public RideService(RideMatchingStrategy rideMatchingStrategy, FareStrategy fareStrategy) {
        this.rideMatchingStrategy = rideMatchingStrategy;
        this.fareStrategy = fareStrategy;
    }

    public List<Ride> getAllRides() {
        return rides;
    }

    public Ride requestRide(Rider rider, int distance, List<Driver> drivers) {
        if (Objects.isNull(rider)) {
            throw new IllegalArgumentException("Rider cannot be null");
        }
        Validator.validateDistance(distance);
        Driver driver = assignDriver(rider, drivers);

        Ride ride = new Ride(IdGenerator.getInstance().nextRideId(),
                rider, driver, distance, RideStatus.ASSIGNED);

        rides.add(ride);

        double totalFare = calculateFair(ride);
        generateFareReceipt(ride.getId(), totalFare);

        return ride;
    }

    public Ride completeRide(int rideId) throws RideNotFoundException {
        Ride ride = getRideById(rideId);
        if (Objects.isNull(ride)) {
            throw new RideNotFoundException("No ride found with id: " + rideId + ". Can't complete!");
        }
        ride.setStatus(RideStatus.COMPLETED);
        return ride;
    }

    private Ride getRideById(int rideId) {
        for (Ride ride : rides) {
            if (ride.getId() == rideId) {
                return ride;
            }
        }
        return null;
    }

    private Driver assignDriver(Rider rider, List<Driver> drivers) {
        return rideMatchingStrategy.findDriver(rider, drivers);
    }

    private double calculateFair(Ride ride) {
        return fareStrategy.calculateFare(ride);
    }

    private void generateFareReceipt(int rideId, double fare) {
        FareReceipt fareReceipt = new FareReceipt(rideId, fare, LocalDateTime.now());
        System.out.println("======= FARE RECEIPT GENERATED =======");
        System.out.println(fareReceipt);
    }

}
