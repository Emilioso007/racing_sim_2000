package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Vehicle.Vehicle;
import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;
import com.eaej.ScreenClasses.ScreenObjects.Button.ButtonHandler;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class LocalPVP extends Screen {

    private final int PLAYER_AMOUNT = 2;

    public static Vehicle[] vehicles;

    private PGraphics[] pG;

    private Level level;

    private final int FIRST_PERSON = 0;
    private final int THIRD_PERSON = 1;
    private int cameraMode = FIRST_PERSON;

    private long startTime;

    private long elapsedTime;

    public LocalPVP(PApplet p) {
        super(p);

        buttonHandler.addButton(new Button(100, 100, 200, 100, "Back", "back_button"));

        level = LevelFactory.createBlobLevel(250, 2000);

        vehicles = new Vehicle[PLAYER_AMOUNT];

        pG = new PGraphics[PLAYER_AMOUNT];

        for (int i = 0; i < pG.length; i++) {
            pG[i] = p.createGraphics(p.width / pG.length, p.height);
        }

        vehicles[0] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3);
        vehicles[1] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3);

        vehicles[0].playerID = Vehicle.PLAYER_WASD;
        vehicles[1].playerID = Vehicle.PLAYER_ARROW;

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
                ScreenManager.setCurrentScreen(new ModeSelect(p));
                break;

            default:
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
        long currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - startTime;

    }

    @Override
    public void render() {
        p.background(0);

        float sectionWidth = p.width / vehicles.length;

        for (int i = 0; i < vehicles.length; i++) {

            pG[i].beginDraw();

            pG[i].background(0);

            Vehicle v = vehicles[i];

            pG[i].pushMatrix();

            if (cameraMode == FIRST_PERSON) {
                pG[i].translate(sectionWidth / 2 - v.getPosition().x, p.height / 2 - v.getPosition().y);
            } else if (cameraMode == THIRD_PERSON) {
                pG[i].translate(sectionWidth / 2, pG[i].height / 2);
                float maxNoise = 0;
                for (PVector point : level.getPoints()) {
                    maxNoise = Math.max(maxNoise, point.z);
                }
                float scale = 0.5f * (Math.min(pG[i].height, pG[i].width)) / maxNoise;
                pG[i].scale(scale);
            }

            showLines(level.getPoints(), 0xFF3B3B3B, 120, pG[i]);
            showLines(level.getPoints(), 0xFFFFFFFF, 5, pG[i]);

            for (Vehicle vehicle : vehicles) {
                showVehicle(vehicle, pG[i]);
            }

            pG[i].fill(255, 0, 0);
            pG[i].ellipse(0, 0, 1, 1);
            pG[i].noFill();

            pG[i].popMatrix();
            pG[i].rect(0, 0, sectionWidth, pG[i].height);
            pG[i].rect(0, 0, sectionWidth * 2, pG[i].height);

            pG[i].endDraw();

        }

        for (int i = 0; i < vehicles.length; i++) {
            p.image(pG[i], i * sectionWidth, 0);
        }

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
