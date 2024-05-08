package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Vehicle.Vehicle;

import processing.core.PApplet;
import processing.core.PVector;

public class LocalPVE extends Screen {

    private Vehicle[] vehicles;

    private Level level;

    private final int FIRST_PERSON = 0;
    private final int THIRD_PERSON = 1;
    private int cameraMode = FIRST_PERSON;

    public LocalPVE(PApplet p) {
        super(p);

        vehicles = new Vehicle[2];

        vehicles[0] = new Vehicle(p, p.width / 2, p.height / 2);
        vehicles[1] = new Vehicle(p, p.width / 2, p.height / 2);

        vehicles[0].playerID = Vehicle.PLAYER_WASD;
        vehicles[1].playerID = Vehicle.PLAYER_AI;

        level = LevelFactory.createBlobLevel(100, 2000);

        for (Vehicle v : vehicles) {
            v.setLevel(level);
        }

    }

    @Override
    public void update() {

        if (KH.clicked("C")) {
            cameraMode = (cameraMode + 1) % 2;
        }
        if (KH.clicked("R")) {
            level = LevelFactory.createBlobLevel(100, 2000);
            for (Vehicle v : vehicles) {
                v.setLevel(level);
            }
        }

        if (KH.clicked("P")) {
            debug = !debug;
        }

        for (Vehicle v : vehicles) {
            v.update();
        }

    }

    @Override
    public void render() {

        p.background(0);

        if (cameraMode == FIRST_PERSON) {
            p.translate(p.width / 2 - vehicles[0].getPosition().x, p.height / 2 -
                    vehicles[0].getPosition().y);
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

        for (Vehicle v : vehicles) {
            showVehicle(v);
        }

    }

}