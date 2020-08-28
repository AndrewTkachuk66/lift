package com.andrew.lift.passenger;

/**
 * Created by Andre on 27.08.2020.
 */
public class PassengerDestination {
    private Enum<UpAndDown> direction;
    private int floorDestination;

    public Enum<UpAndDown> getDirection() {
        return direction;
    }

    public void setDirection(Enum<UpAndDown> direction) {
        this.direction = direction;
    }

    public int getFloorDestination() {
        return floorDestination;
    }

    public void setFloorDestination(int floorDestination) {
        this.floorDestination = floorDestination;
    }
}
