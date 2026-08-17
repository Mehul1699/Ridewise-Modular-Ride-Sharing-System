package com.ridewise.util;

public class Validator {

    public static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public static void validateLocation(String location) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Invalid Location");
        }
    }

    public static void validateDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance should be greater than 0");
        }
    }

}
