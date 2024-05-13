package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Vehicle.Vehicle;
import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;
import processing.core.PVector;

public class LocalPVE extends Screen {

    public static Vehicle[] vehicles;

    private Level level;

    private final int FIRST_PERSON = 0;
    private final int THIRD_PERSON = 1;
    private int cameraMode = FIRST_PERSON;

    private long startTime;

    private long elapsedTime;

    public LocalPVE(PApplet p, int aiAmount, int difficulty) {
        super(p);

        buttonHandler.addButton(new Button(100, 100, 200, 100, "Back", "back_button"));

        level = LevelFactory.createBlobLevel(100, 2000);

        vehicles = new Vehicle[6];

        vehicles[0] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3);
        vehicles[1] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3);
        vehicles[2] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 4);
        vehicles[3] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 5);
        vehicles[4] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 6);
        vehicles[5] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 6);

        vehicles[0].playerID = Vehicle.PLAYER_WASD;
        vehicles[1].playerID = Vehicle.PLAYER_AI;
        vehicles[2].playerID = Vehicle.PLAYER_AI;
        vehicles[3].playerID = Vehicle.PLAYER_AI;
        vehicles[4].playerID = Vehicle.PLAYER_AI;
        vehicles[5].playerID = Vehicle.PLAYER_AI;

        for (Vehicle v : vehicles) {
            v.setLevel(level);
        }

        startTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        buttonHandler.update();
        switch (buttonHandler.buttonClicked) {
            case "back_button":
                ScreenManager.setCurrentScreen(new LocalPVESettings(p));
                break;
        }

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

        if(vehicles[0].hitCheckpoint(level.getCurrentCheckpoint())) {
            level.setCurrentCheckpoint(level.getPoints().get((level.getPoints().indexOf(level.getCurrentCheckpoint()) + 5) % level.getPoints().size()));
        }

        long currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - startTime;

    }

    @Override
    public void render() {

        p.background(0);
        p.pushMatrix();
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
        
        for (int i = 0; i < vehicles.length; i++) {
            showVehicle(vehicles[i], carImages[i==0?0:((i%4)+1)]);
        }

        p.noFill();
        p.stroke(0xFF00FF00);
        p.strokeWeight(5);
        p.ellipse(level.getCurrentCheckpoint().x, level.getCurrentCheckpoint().y, 120, 120);

        p.popMatrix();
        timer();

        buttonHandler.render();
    }

    public static Vehicle[] getVehicles() {
        return vehicles;
    }

    public void timer() {
        p.fill(255);
        p.textSize(32);
        p.text(elapsedTime / 1000f, p.width - 100, 30);
    }
}
