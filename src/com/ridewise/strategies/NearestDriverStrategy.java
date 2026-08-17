package com.ridewise.strategies;

import com.ridewise.interfaces.RideMatchingStrategy;
import com.ridewise.model.Driver;
import com.ridewise.model.Rider;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class NearestDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        double minimumDistance = Double.MAX_VALUE;
        Driver nearestDriver = null;
        for (Driver driver : drivers) {
            double distance = generateDistance();

            if (distance < minimumDistance) {
                minimumDistance = distance;
                nearestDriver = driver;
            }
        }
        if (Objects.nonNull(nearestDriver)) {
            nearestDriver.setAvailable(Boolean.FALSE);
        }
        return nearestDriver;
    }

    private double generateDistance() {
        Random random = new Random();

        return 1 + random.nextDouble() * 9;
    }
}
