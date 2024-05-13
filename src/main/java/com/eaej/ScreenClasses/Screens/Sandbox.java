package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Vehicle.Vehicle;

import processing.core.PApplet;
import processing.core.PVector;

public class Sandbox extends Screen {

    Level level;
    Vehicle vehicle;

    private final int FIRST_PERSON = 0;
    private final int THIRD_PERSON = 1;
    private int cameraMode = FIRST_PERSON;

    public Sandbox(PApplet p) {
        super(p);

        level = LevelFactory.createBlobLevel(100, 2000);
        vehicle = new Vehicle(p, p.width / 2, p.height / 2, 3);
        vehicle.setLevel(level);
    }

    @Override
    public void update() {

        if (KH.clicked("C")) {
            cameraMode = (cameraMode + 1) % 2;
        }
        if (KH.clicked("R")) {
            level = LevelFactory.createBlobLevel(100, 2000);
            vehicle.setLevel(level);
        }

        if (KH.pressed("W")) {
            vehicle.applyForce(vehicle.getHeading());
        }
        if (KH.pressed("S")) {
            vehicle.applyForce(vehicle.getHeading().mult(-1));
        }
        if (KH.pressed("A")) {
            vehicle.rotate(-0.1f);
        }
        if (KH.pressed("D")) {
            vehicle.rotate(0.1f);
        }

        vehicle.update();

    }

    @Override
    public void render() {

        p.background(0);

        if (cameraMode == FIRST_PERSON) {
            p.translate(p.width / 2 - vehicle.getPosition().x, p.height / 2 -
                    vehicle.getPosition().y);
        } else if (cameraMode == THIRD_PERSON) {
            p.translate(p.width / 2, p.height / 2);
            float maxNoise = 0;
            for (PVector point : level.getPoints()) {
                maxNoise = Math.max(maxNoise, point.z);
            }
            float scale = 0.5f * p.height / maxNoise;
            p.scale(scale);
            System.out.println(scale);
        }

        showLines(level.getPoints(), 0xFF3B3B3B, 120);
        showLines(level.getPoints(), 0xFFFFFFFF, 5);

        showVehicle(vehicle);

    }

}
