package com.ridewise.strategies;

import com.ridewise.interfaces.FareStrategy;
import com.ridewise.model.Constants;
import com.ridewise.model.Ride;

public class PeakHourFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(Ride ride) {
        double distance = ride.getDistance();
        return (Constants.BASE_FARE + (distance * Constants.RATE)) * Constants.PEAK_MULTIPLIER;
    }
}
