package com.ridewise.service;

import com.ridewise.exception.RiderNotFoundException;
import com.ridewise.model.Rider;
import com.ridewise.util.IdGenerator;
import com.ridewise.util.Validator;

import java.util.*;

public class RiderService {
    private List<Rider> riders = new ArrayList<>();

    public Rider registerRider(String name, String location) {
        Validator.validateName(name);
        Validator.validateLocation(location);
        Rider rider = new Rider(IdGenerator.getInstance().nextRiderId(), name, location);
        riders.add(rider);
        return rider;
    }

    public Rider getRiderById(int id) throws RiderNotFoundException {
        for (Rider rider : riders) {
            if (rider.getId() == id) {
                return rider;
            }
        }
        throw new RiderNotFoundException("Rider not found with id: " + id);
    }
}
