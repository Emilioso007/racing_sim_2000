package com.eaej.LogicClasses.Vehicle;

import processing.core.PVector;

public class Vehicle {

    public PVector pos, vel, acc;

    public float maxSpeed = 5;

    public Vehicle(float x, float y) {
        pos = new PVector(x, y);
        vel = new PVector(0, 0);
        acc = new PVector(0, 0);
    }

    public void update() {
        vel.add(acc);
        pos.add(vel);
        acc.mult(0);

        vel.limit(maxSpeed);
    }

    public void applyForce(PVector force) {
        acc.add(force);
    }

    public PVector getHeading() {
        return PVector.fromAngle(vel.heading());
    }

    public void rotate(float f) {
        vel.rotate(f);
    }

    public PVector getPosition() {
        return pos;
    }

}
