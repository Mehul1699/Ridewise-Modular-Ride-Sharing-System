package com.ridewise;

import com.ridewise.enums.VehicleType;
import com.ridewise.model.Driver;
import com.ridewise.model.Ride;
import com.ridewise.model.Rider;
import com.ridewise.service.DriverService;
import com.ridewise.service.RideService;
import com.ridewise.service.RiderService;
import com.ridewise.strategies.LeastActiveDriverStrategy;
import com.ridewise.strategies.PeakHourFareStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final RiderService riderService = new RiderService();
    private static final DriverService driverService = new DriverService();
    private static final RideService rideService = new RideService(
            new LeastActiveDriverStrategy(),
            new PeakHourFareStrategy()
    );

    public static void main(String[] args) {
        int choice = 0;
        while (choice != 7) {
            System.out.println("==== Ridewise-Ride-Sharing-System ====");
            System.out.println("Please enter an option");
            System.out.println("1. Add Rider");
            System.out.println("2. Add Driver");
            System.out.println("3. View Available Drivers");
            System.out.println("4. Request Ride");
            System.out.println("5. Complete Ride");
            System.out.println("6. View Rides");
            System.out.println("7. Exit");

            System.out.println("Please enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addRider(scanner);
                    break;
                case 2:
                    addDriver(scanner);
                    break;
                case 3:
                    viewAvailableDrivers();
                    break;
                case 4:
                    requestRide(scanner);
                    break;
                case 5:
                    completeRide(scanner);
                    break;
                case 6:
                    viewRides();
                    break;
                case 7:
                    System.out.println("THANK YOU!!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void addRider(Scanner scanner) {
        System.out.println("Please enter name of the rider: ");
        String name = scanner.nextLine();
        System.out.println("Please enter location of the rider: ");
        String location = scanner.nextLine();
        try {
            Rider rider = riderService.registerRider(name, location);
            System.out.println("Rider created: " + rider);
        } catch (Exception e) {
            System.out.println("Exception in registering rider: " + e.getLocalizedMessage());
        }
    }

    public static void addDriver(Scanner scanner) {
        System.out.println("Please enter name of the driver: ");
        String name = scanner.nextLine();
        System.out.println("Please enter location of the driver: ");
        String location = scanner.nextLine();
        VehicleType vehicleType = null;
        while (vehicleType == null) {
            try {
                System.out.println("Please enter vehicle type (BIKE/AUTO/CAR): ");
                vehicleType = VehicleType.valueOf(scanner.next().toUpperCase());
            } catch (Exception e) {
                System.out.println("Invalid Vehicle type. Try again.");
            }
        }

        try {
            Driver driver = driverService.registerDrivers(name, location, true, vehicleType);
            System.out.println("Driver created: " + driver);
        } catch (Exception e) {
            System.out.println("Exception occurred in registering driver: " + e.getLocalizedMessage());
        }
    }

    public static void viewAvailableDrivers() {
        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        System.out.println("==== AVAILABLE DRIVERS ====");
        for (Driver driver : availableDrivers) {
            System.out.println(driver);
        }
    }

    public static void requestRide(Scanner scanner) {
        System.out.println("Enter rider id: ");
        int riderId = scanner.nextInt();
        System.out.println("Enter distance for the ride: ");
        int distance = scanner.nextInt();
        scanner.nextLine();
        try {
            Rider rider = riderService.getRiderById(riderId);
            List<Driver> drivers = driverService.getAvailableDrivers();
            if (drivers.isEmpty()) {
                System.out.println("No drivers available at the moment. Please try after sometime");
                return;
            }
            Ride ride = rideService.requestRide(rider, distance, drivers);
            System.out.println("Ride scheduled: " + ride);
        } catch (Exception e) {
            System.out.println("Exception occurred during request ride: " + e.getLocalizedMessage() + ". Cannot schedule a ride");
        }
    }

    public static void completeRide(Scanner scanner) {
        System.out.println("Add the id of the ride to be marked completed: ");
        int rideId = scanner.nextInt();
        try {
            Ride ride = rideService.completeRide(rideId);
            driverService.updateAvailability(ride.getDriver().getId(), Boolean.TRUE);
            System.out.println("Ride completed: " + ride);
        } catch (Exception e) {
            System.out.println("Exception occurred while completing ride: " + e.getLocalizedMessage());
        }
    }

    public static void viewRides() {
        List<Ride> rides = rideService.getAllRides();
        for (Ride ride : rides) {
            System.out.println(ride);
        }
    }

}
