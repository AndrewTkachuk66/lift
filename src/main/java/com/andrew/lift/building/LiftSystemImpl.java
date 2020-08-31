package com.andrew.lift.building;

import com.andrew.lift.passenger.PassengerDestination;
import com.andrew.lift.passenger.UpAndDown;
import com.andrew.lift.random.RandomGenerator;

import java.util.Map;


/**
 * Created by Andre on 27.08.2020.
 */

public class LiftSystemImpl implements LiftSystem {

    private int liftCapacity = 5;

    @Override
    public void startMove(int numberOfFloors, Map<Integer, PassengerDestination> takenPassengers) {

        System.out.println("Number of floors - " + numberOfFloors);
        System.out.println("Lift capacity - " + liftCapacity);

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

            int passengersIn = checkIfPassengersInLiftUp(currentFloor, eachPassengerDestination, takenPassengers);
            System.out.println("Passengers in - " + passengersIn);
            if (passengersIn > 0) {
                takenPassengers = takePassengersLiftUp(currentFloor, eachPassengerDestination, passengersIn, takenPassengers);
            }

            int passengersOut = checkIfPassengersOut(currentFloor, takenPassengers);
            System.out.println("passengers out - " + passengersOut);
            if (passengersOut > 0) {
                passengersOut(currentFloor, takenPassengers);
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
            int passengersIn = checkIfPassengersInLiftDown(currentFloor, eachPassengerDestination, takenPassengers);
            System.out.println("Passengers in - " + passengersIn);
            if (passengersIn > 0) {
                takenPassengers = takePassengersLiftDown(currentFloor, eachPassengerDestination, passengersIn, takenPassengers);
            }
            int passengersOut = checkIfPassengersOut(currentFloor, takenPassengers);
            System.out.println("passengers out - " + passengersOut);
            if (passengersOut > 0) {
                passengersOut(currentFloor, takenPassengers);
            }
            System.out.println("Passengers in lift - " + takenPassengers.size());
            System.out.println("---------");
        }
    }

    private Map<Integer, PassengerDestination> takePassengersLiftUp(int currentFloor, Map<Integer, PassengerDestination> eachPassengerDestination, int passengersIn, Map<Integer, PassengerDestination> takenPassengers) {
        for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
            PassengerDestination passengerDestination = entry.getValue();
            if (takenPassengers.size() < 5 && passengerDestination.getFloorDestination() > currentFloor && passengerDestination.getDirection() == UpAndDown.UP) {
                takenPassengers.put(takenPassengers.size() + 1, passengerDestination);
            }
        }
        return takenPassengers;
    }

    private Map<Integer, PassengerDestination> takePassengersLiftDown(int currentFloor, Map<Integer, PassengerDestination> eachPassengerDestination, int passengersIn, Map<Integer, PassengerDestination> takenPassengers) {
        for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
            PassengerDestination passengerDestination = entry.getValue();
            if (takenPassengers.size() < 5 && passengerDestination.getFloorDestination() < currentFloor && passengerDestination.getDirection() == UpAndDown.DOWN) {
                takenPassengers.put(takenPassengers.size() + 1, passengerDestination);
            }

        }
        return takenPassengers;
    }

    private int checkIfPassengersInLiftUp(int currentFloor, Map<Integer, PassengerDestination> eachPassengerDestination, Map<Integer, PassengerDestination> takenPassengers) {
        int passengersIn = 0;
        if (takenPassengers.size() < liftCapacity) {
            for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
                PassengerDestination passengerDestination = entry.getValue();
                if(passengerDestination.getFloorDestination() > currentFloor && passengerDestination.getDirection() == UpAndDown.UP){
                    if(takenPassengers.size() + 1 <= liftCapacity){
                        passengersIn++;
                    }
                }
            }
        }
        return passengersIn;
    }

    private int checkIfPassengersInLiftDown(int currentFloor, Map<Integer, PassengerDestination> eachPassengerDestination, Map<Integer, PassengerDestination> takenPassengers) {
        int passengersIn = 0;
        if (takenPassengers.size() < liftCapacity) {
            for (Map.Entry<Integer, PassengerDestination> entry : eachPassengerDestination.entrySet()) {
                PassengerDestination passengerDestination = entry.getValue();
                if(passengerDestination.getFloorDestination() < currentFloor && passengerDestination.getDirection() == UpAndDown.DOWN){
                    if(takenPassengers.size() + 1 <= liftCapacity){
                        passengersIn++;
                    }
                }
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
