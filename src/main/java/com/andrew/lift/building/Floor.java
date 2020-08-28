package com.andrew.lift.building;

import com.andrew.lift.passenger.PassengerDestination;
import com.andrew.lift.random.RandomGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andre on 27.08.2020.
 */
public class Floor {
    private static final RandomGenerator generator = new RandomGenerator();
    public static int numberOfPassengers = generator.generateNumberOfPassengers();


    public Map<Integer, PassengerDestination> forEachPassenderGenerateDestination(int numberOfPassengers) {
        Map<Integer, PassengerDestination> eachPassengersDestination = new HashMap<Integer, PassengerDestination>();
        for (int i = 1; i <= numberOfPassengers; i++) {
            eachPassengersDestination.put(i, generator.generatePassengerDestination());
        }
        return eachPassengersDestination;
    }
}
