package com.andrew.lift;

import com.andrew.lift.building.LiftSystem;
import com.andrew.lift.building.LiftSystemImpl;
import com.andrew.lift.random.RandomGenerator;

/**
 * Created by Andre on 28.08.2020.
 */
public class LiftClient {
   private RandomGenerator generator;

    public LiftClient(RandomGenerator generator) {
        this.generator = generator;
    }

    public static void main(String[] args) {
        LiftSystem lift = new LiftSystemImpl();
        lift.startMove(RandomGenerator.numberOfFloors);
    }
}
