package com.andrew.lift.building;

import com.andrew.lift.passenger.PassengerDestination;

import java.util.Map;

/**
 * Created by Andre on 28.08.2020.
 */
public interface LiftSystem {
    void startMove(int numberOfFloors, Map<Integer, PassengerDestination> takenPassengers);
}
