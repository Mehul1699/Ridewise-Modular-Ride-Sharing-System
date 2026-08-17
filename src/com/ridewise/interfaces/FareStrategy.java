package com.ridewise.interfaces;

import com.ridewise.model.Ride;

public interface FareStrategy {
    double calculateFare(Ride ride);
}
