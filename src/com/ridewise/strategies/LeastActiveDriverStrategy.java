package com.ridewise.strategies;

import com.ridewise.enums.RideStatus;
import com.ridewise.interfaces.RideMatchingStrategy;
import com.ridewise.model.Driver;
import com.ridewise.model.Ride;
import com.ridewise.model.Rider;
import com.ridewise.service.RideService;

import java.util.List;
import java.util.Objects;

public class LeastActiveDriverStrategy implements RideMatchingStrategy {

    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        int minimumRides = Integer.MAX_VALUE;
        Driver driver = null;
        for (Driver driver1 : drivers) {
            int total = driver1.getCompletedRideCount();
            if (total < minimumRides) {
                minimumRides = total;
                driver = driver1;
            }
        }
        if (Objects.nonNull(driver)) {
            driver.setAvailable(Boolean.FALSE);
        }
        return driver;
    }
}
