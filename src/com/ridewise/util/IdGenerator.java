package com.ridewise.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final IdGenerator INSTANCE;

    private AtomicInteger riderIdGenerator;
    private AtomicInteger driverIdGenerator;
    private AtomicInteger rideIdGenerator;

    static {
        System.out.println("Initializing Id Generator");
        INSTANCE = new IdGenerator();
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    private IdGenerator() {
        riderIdGenerator = new AtomicInteger(999);
        driverIdGenerator = new AtomicInteger(99);
        rideIdGenerator = new AtomicInteger(0);
    }

    public int nextRiderId() {
        return riderIdGenerator.incrementAndGet();
    }

    public int nextDriverId() {
        return driverIdGenerator.incrementAndGet();
    }

    public int nextRideId() {
        return rideIdGenerator.incrementAndGet();
    }

}
