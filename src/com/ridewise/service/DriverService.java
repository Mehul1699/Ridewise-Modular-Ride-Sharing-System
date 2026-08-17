package com.ridewise.service;

import com.ridewise.enums.VehicleType;
import com.ridewise.exception.DriverNotFoundException;
import com.ridewise.model.Driver;
import com.ridewise.util.IdGenerator;
import com.ridewise.util.Validator;

import java.util.*;

public class DriverService {

    private List<Driver> drivers = new ArrayList<>();

    public Driver registerDrivers(String name, String currentLocation,
                                  boolean isAvailable, VehicleType vehicleType) {
        Validator.validateName(name);
        Validator.validateLocation(currentLocation);

        Driver driver = new Driver(IdGenerator.getInstance().nextDriverId(), name,
                currentLocation, isAvailable, vehicleType, 0);

        drivers.add(driver);
        return driver;
    }

    private Driver getDriverById(int id) {
        for (Driver driver : drivers) {
            if (driver.getId() == id) {
                return driver;
            }
        }
        return null;
    }

    public void updateAvailability(int id, boolean isAvailable) throws DriverNotFoundException {
        Driver driver = getDriverById(id);
        if (Objects.isNull(driver)) {
            throw new DriverNotFoundException("Driver not found with id: " + id + ". Cannot update availability");
        }
        driver.setAvailable(isAvailable);
        int completedRides = driver.getCompletedRideCount();
        driver.setCompletedRideCount(++completedRides);
    }

    public List<Driver> getAvailableDrivers() {
        return drivers.stream().filter(Driver::isAvailable).toList();
    }

}
