## RideWise — Modular Ride-Sharing System

A console-based Ride Booking System built in Java to demonstrate core Object-Oriented Programming concepts, service-layer design, collections, exception handling, and clean separation of responsibilities.

**Overview**

The application allows riders to request and manage rides while drivers can be assigned to rides based on their availability.

The system maintains ride and driver information in memory and provides a menu-driven interface through the main/test runner class.

**Features**

- Register and manage riders
- Register and manage drivers
- View available drivers
- Request a ride
- Assign an available driver to a ride
- View rides
- Complete a ride
- Automatically make a driver available after completing a ride


**Technologies Used**

- Java
- Java Collections Framework
- Object-Oriented Programming
- Exception Handling
- In-memory data storage
- Console-based user interface

**Project Structure**

```text
src/
└── main/
    └── java/
        └── com.ridewise/
            ├── model/
            │   ├── Constants.java
            │   ├── FareReceipt.java
            │   ├── Person.java
            │   ├── Driver.java
            │   ├── Rider.java
            │   └── Ride.java
            │
            ├── enums/
            │   ├── RideStatus.java
            │   └── VehicleType.java
            │
            ├── service/
            │   ├── DriverService.java
            │   ├── RiderService.java
            │   └── RideService.java
            │
            ├── strategies/
            │   ├── NearestDriverStrategy.java
            │   ├── LeastActiveDriverStrategy.java
            │   ├── DefaultFareStrategy.java
            │   └── PeakHourFareStrategy.java
            │
            ├── interfaces/
            │   ├── FareStrategy.java
            │   └── RideMatchingStrategy.java
            │
            ├── util/
            │   ├── IdGenerator.java
            │   └── Validator.java
            │
            └── Main.java
```

**Core Classes**

___Driver___

Represents a driver in the system.

Typical attributes include:

- Driver ID
- Driver name
- Vehicle details
- Availability status
- Number of completed rides


___Rider___

Represents a customer who can request rides.

Typical attributes include:

- Rider ID
- Rider name
- Contact information


___Ride___

Represents a ride requested by a rider.

Typical attributes include:

- Ride ID
- Rider
- Driver
- Distance of Ride
- Ride status


**Services**

DriverService

Responsible for driver-related operations such as:

- Adding drivers
- Finding drivers
- Finding available drivers
- Updating driver availability
- Updating completed ride count


**RiderService**

Responsible for rider-related operations such as:

- Adding riders
- Finding riders
- Viewing rider information


**RideService**

Responsible for ride-related operations such as:

- Creating rides
- Assigning drivers
- Finding rides
- Viewing rides
- Completing rides

The RideService handles the ride's state, while driver-specific changes are delegated to DriverService.

**Ride Lifecycle**

A ride follows a simple lifecycle:

```text
Ride Requested
      ↓
Driver Assigned
      ↓
Ride In Progress
      ↓
Ride Completed
```

When a ride is completed:

Ride → COMPLETED

Driver → AVAILABLE

Driver.completedRides → +1

**Handling Driver Availability**

Only available drivers can be assigned to a new ride.

If no driver is available, the system handles the situation gracefully instead of attempting to assign a null driver.

Example:

No drivers available at the moment.

Once a ride is completed, the assigned driver becomes available again.

**Exception Handling**

The application uses custom exceptions for invalid operations, such as:

- Driver not found
- Ride not found
- Rider not found
- Invalid input

Example:
```text
throw new DriverNotFoundException(
"Driver not found with id: " + id
);
```

**How to Run**

___Prerequisites___

Make sure Java is installed:

java -version

and:

javac -version

**Run the Application**

Clone the repository:

git clone <repository-url>

Navigate to the project:

cd <project-directory>

Compile and run the application using your IDE or the configured Java build system.

If the project uses a Main class, run that class to start the application.

**Sample Menu**

===== RIDE BOOKING SYSTEM =====

1. Add Rider
2. Add Driver
3. Request Ride
4. View Available Drivers
5. View Rides
6. Complete Ride
7. Exit

Enter your choice:

**Design Principles**

The project focuses on applying fundamental software design principles:

**Encapsulation**

Entity fields are kept private and accessed through appropriate getters/setters.

**Separation of Responsibilities**

Different services are responsible for different domains:

DriverService → Driver-related operations

RiderService  → Rider-related operations

RideService   → Ride-related operations

**Reusability**

Common operations are implemented in service classes instead of being duplicated throughout the application.

**Exception Handling**

Invalid operations are handled using meaningful exceptions rather than allowing unexpected failures.

**Data Storage**

The application currently uses in-memory storage. Data is maintained while the application is running and is not persisted to a database.