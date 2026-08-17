package com.ridewise.interfaces;

import com.ridewise.model.Driver;
import com.ridewise.model.Rider;

import java.util.List;

public interface RideMatchingStrategy {
    Driver findDriver(Rider rider, List<Driver> drivers);
}
