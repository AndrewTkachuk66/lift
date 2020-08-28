package com.andrew.lift.building;

import com.andrew.lift.passenger.PassengerDestination;
import com.andrew.lift.passenger.UpAndDown;
import com.andrew.lift.random.RandomGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andre on 27.08.2020.
 */
public class LiftSystemImpl implements LiftSystem {

    int liftCapacity = 5;
    int currentLiftCapacity = 0;

    @Override
    public void startMove(int numberOfFloors) {
        goUp(numberOfFloors);
        goDown(numberOfFloors);
    }

    private void goUp(int numberOfFloors) {
        for (int currentFloor = 1; currentFloor <= numberOfFloors; currentFloor++) {
            System.out.println("Floor number - " + currentFloor);
            Floor floor = new Floor();
            Map<Integer, PassengerDestination> takenPassengers = new HashMap<Integer, PassengerDestination>();

            Map<Integer, PassengerDestination> eachPassengerDestination = floor.forEachPassenderGenerateDestination(Floor.numberOfPassengers);

            int passengersIn = checkIfPassengersIn(currentFloor, eachPassengerDestination);
            if (passengersIn > 0) {
                takenPassengers = takePassengers(eachPassengerDestination, passengersIn);
            }

            int passengersOut = checkIfPassengersOut(currentFloor, takenPassengers);
            if (passengersOut > 0) {
                passengersOut(passengersOut);
            }
        }
    }

    private void goDown(int numberOfFloors) {
        for (int currentFloor = numberOfFloors; currentFloor >= 1; currentFloor--) {
            Floor floor = new Floor();
            Map<Integer, PassengerDestination> takenPassengers = new HashMap<Integer, PassengerDestination>();

            Map<Integer, PassengerDestination> eachPassengerDestination = floor.forEachPassenderGenerateDestination(Floor.numberOfPassengers);
            int passengersIn = checkIfPassengersIn(currentFloor, eachPassengerDestination);
            if (passengersIn > 0) {
                takenPassengers = takePassengers(eachPassengerDestination, passengersIn);
            }
            int passengersOut = checkIfPassengersOut(currentFloor, takenPassengers);
            if (passengersOut > 0) {
                passengersOut(passengersOut);
            }
        }
    }

    private Map<Integer, PassengerDestination> takePassengers(Map<Integer, PassengerDestination> eachPassengerDestination, int passengersIn) {
        Map<Integer, PassengerDestination> takenPassengers = new HashMap<Integer, PassengerDestination>();
        if (currentLiftCapacity + passengersIn <= liftCapacity) {
            for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
                Integer currentPassenger = entry.getKey();
                PassengerDestination passengerDestination = entry.getValue();
                if (passengerDestination.getDirection() == UpAndDown.UP) {
                    takenPassengers.put(currentPassenger, passengerDestination);
                    currentLiftCapacity++;
                }
            }
        }
        return takenPassengers;
    }

    private int checkIfPassengersIn(int currentFloor, Map<Integer, PassengerDestination> eachPassengerDestination) {
        for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
            int passengersIn = 0;
            PassengerDestination passengerDestination = entry.getValue();
            if (currentFloor != RandomGenerator.numberOfFloors && passengerDestination.getDirection() == UpAndDown.UP) {
                passengersIn++;
                return passengersIn;
            }
            if (currentFloor != 1 && passengerDestination.getDirection() == UpAndDown.DOWN) {
                passengersIn++;
                return passengersIn;
            }
        }
        return 0;
    }

    private void passengersOut(int passengersOut) {
        currentLiftCapacity -= passengersOut;
    }

    private int checkIfPassengersOut(int currentFloor, Map<Integer, PassengerDestination> eachPassengerDestination) {
        for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
            int passengersOut = 0;
            //Integer currentPassenger = entry.getKey();
            PassengerDestination passengerDestination = entry.getValue();
            if (passengerDestination.getFloorDestination() == currentFloor) {
                passengersOut++;
                return passengersOut;
            }
        }
        return 0;
    }
}
