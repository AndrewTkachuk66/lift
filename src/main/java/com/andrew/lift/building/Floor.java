package com.andrew.lift.building;

import com.andrew.lift.passenger.PassengerDestination;
import com.andrew.lift.random.RandomGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andre on 27.08.2020.
 */
public class Floor {
    private RandomGenerator generator = new RandomGenerator();
    private int numberOfPassengers =
            generator.generateNumberOfPassengers();

    public Floor(RandomGenerator generator) {
        this.generator = generator;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public Map<Integer, PassengerDestination> forEachPassengerGenerateDestination(int numberOfPassengers) {
        Map<Integer, PassengerDestination> eachPassengersDestination = new HashMap<Integer, PassengerDestination>();
        for (int i = 1; i <= numberOfPassengers; i++) {
            eachPassengersDestination.put(i, generator.generatePassengerDestination());
        }
        return eachPassengersDestination;
    }
}
