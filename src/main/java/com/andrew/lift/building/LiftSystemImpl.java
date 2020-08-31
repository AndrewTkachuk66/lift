package com.andrew.lift.building;

import com.andrew.lift.passenger.PassengerDestination;
import com.andrew.lift.passenger.UpAndDown;
import com.andrew.lift.random.RandomGenerator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by Andre on 27.08.2020.
 */

public class LiftSystemImpl implements LiftSystem {

    private static final int LIFT_CAPACITY = 5;

    @Override
    public void startMove(int numberOfFloors, Map<Integer, PassengerDestination> takenPassengers) {

        System.out.println("Number of floors - " + numberOfFloors);
        System.out.println("Lift capacity - " + LIFT_CAPACITY);

        goUp(numberOfFloors, takenPassengers);
        goDown(numberOfFloors, takenPassengers);
    }

    private void goUp(int numberOfFloors, Map<Integer, PassengerDestination> takenPassengers) {
        System.out.println("Go Up:");
        int currentFloor = 1;
        while (currentFloor <= numberOfFloors) {
            Floor floor = new Floor(new RandomGenerator());
            System.out.println("Floor number - " + currentFloor);
            Map<Integer, PassengerDestination> eachPassengerDestination = floor.forEachPassengerGenerateDestination(floor.getNumberOfPassengers());
            int passengersIn = takePassengersLiftUp(currentFloor, eachPassengerDestination, takenPassengers);
            System.out.println("Passengers in - " + passengersIn);

            int passengersOut = checkIfPassengersOut(currentFloor, takenPassengers);
            System.out.println("passengers out - " + passengersOut);
            if (passengersOut > 0) {
                passengersOut(currentFloor, takenPassengers);
                takenPassengers = putInOrderTakenPassengers(takenPassengers);
            }
            System.out.println("Passengers in lift - " + takenPassengers.size());
            System.out.println("---------");
            currentFloor++;
        }
    }

    private void goDown(int numberOfFloors, Map<Integer, PassengerDestination> takenPassengers) {
        System.out.println("Go down:");
        for (int currentFloor = numberOfFloors; currentFloor >= 1; currentFloor--) {
            RandomGenerator randomGenerator = new RandomGenerator();
            Floor floor = new Floor(randomGenerator);
            System.out.println("Floor number - " + currentFloor);

            Map<Integer, PassengerDestination> eachPassengerDestination = floor.forEachPassengerGenerateDestination(floor.getNumberOfPassengers());
            int passengersIn = takePassengersLiftDown(currentFloor, eachPassengerDestination, takenPassengers);
            System.out.println("Passengers in - " + passengersIn);
            int passengersOut = checkIfPassengersOut(currentFloor, takenPassengers);
            System.out.println("passengers out - " + passengersOut);
            if (passengersOut > 0) {
                passengersOut(currentFloor, takenPassengers);
                takenPassengers = putInOrderTakenPassengers(takenPassengers);
            }
            System.out.println("Passengers in lift - " + takenPassengers.size());
            System.out.println("---------");
        }
    }

    private Map<Integer, PassengerDestination> putInOrderTakenPassengers(Map<Integer, PassengerDestination> takenPassengers) {
        int i = 1;
        Map<Integer, PassengerDestination> orderedTakenPassengers = new HashMap<>();
        Iterator<Map.Entry<Integer, PassengerDestination>> iterator = takenPassengers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, PassengerDestination> entry = iterator.next();
            PassengerDestination passengerDestination = entry.getValue();
            iterator.remove();
            orderedTakenPassengers.put(new Integer(i++), passengerDestination);
        }
        return orderedTakenPassengers;
    }

    private int takePassengersLiftUp(int currentFloor, Map<Integer, PassengerDestination> eachPassengerDestination, Map<Integer, PassengerDestination> takenPassengers) {
        int passengersIn = 0;
        for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
            PassengerDestination passengerDestination = entry.getValue();
            if (takenPassengers.size() < LIFT_CAPACITY && passengerDestination.getFloorDestination() > currentFloor && passengerDestination.getDirection() == UpAndDown.UP) {
                takenPassengers.put(takenPassengers.size() + 1, passengerDestination);
                passengersIn++;
            }
        }
        return passengersIn;
    }

    private int takePassengersLiftDown(int currentFloor, Map<Integer, PassengerDestination> eachPassengerDestination, Map<Integer, PassengerDestination> takenPassengers) {
        int passengersIn = 0;
        for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
            PassengerDestination passengerDestination = entry.getValue();
            if (takenPassengers.size() < LIFT_CAPACITY && passengerDestination.getFloorDestination() < currentFloor && passengerDestination.getDirection() == UpAndDown.DOWN) {
                takenPassengers.put(takenPassengers.size() + 1, passengerDestination);
                passengersIn++;
            }

        }
        return passengersIn;
    }

    private void passengersOut(int currentFloor, Map<Integer, PassengerDestination> takenPassengers) {
        takenPassengers.entrySet().removeIf(entry -> entry.getValue().getFloorDestination() == currentFloor);
    }

    private int checkIfPassengersOut(int currentFloor, Map<Integer, PassengerDestination> takenPassengers) {
        int passengersOut = 0;
        for (Map.Entry<Integer, PassengerDestination> entry : takenPassengers.entrySet()) {
            if (entry.getValue().getFloorDestination() == currentFloor) {
                passengersOut++;
            }
        }
        return passengersOut;
    }
}
