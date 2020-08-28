package com.andrew.lift.random;

import com.andrew.lift.passenger.PassengerDestination;
import com.andrew.lift.passenger.UpAndDown;

import java.util.Random;

/**
 * Created by Andre on 27.08.2020.
 */
public class RandomGenerator {
    private static final  Random random = new Random();
    public static final int numberOfFloors = generateNumberOfFloors();

    public static int getNumberOfFloors() {
        return numberOfFloors;
    }

    private static int generateNumberOfFloors(){
       return  5 + random.nextInt(20 - 5 + 1);
    }

    public int generateNumberOfPassengers(){
        return 1 + random.nextInt(5);
    }

    public Enum<UpAndDown> generateUpOrDown(){

        return UpAndDown.values()[new Random().nextInt(UpAndDown.values().length)];
    }

    public int generatePassengerFloorDestination(){
        return 1 + random.nextInt(numberOfFloors);
    }

    public PassengerDestination generatePassengerDestination(){
        PassengerDestination passengerDestination = new PassengerDestination();
        passengerDestination.setDirection(generateUpOrDown());
        passengerDestination.setFloorDestination(generatePassengerFloorDestination());
        return passengerDestination;
    }
}
