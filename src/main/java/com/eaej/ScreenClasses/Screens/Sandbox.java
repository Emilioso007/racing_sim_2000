package com.eaej.ScreenClasses.Screens;

import java.util.ArrayList;

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
        vehicle = new Vehicle(p.width / 2, p.height / 2);
    }

    @Override
    public void update() {

        if (KH.clicked("C")) {
            cameraMode = (cameraMode + 1) % 2;
        }
        if (KH.clicked("R")) {
            level = LevelFactory.createBlobLevel(100, 2000);
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

        showLines(level.getPoints(), 0xFFFFFFFF, 10);

        showVehicle(vehicle);

    }

    private void showVehicle(Vehicle vehicle2) {

        ArrayList<PVector> points = new ArrayList<PVector>();

        PVector heading = vehicle2.getHeading().copy().mult(20);
        PVector left = heading.copy().rotate(PApplet.radians(135));
        PVector right = heading.copy().rotate(PApplet.radians(-135));

        points.add(vehicle2.getPosition().copy().add(heading));
        points.add(vehicle2.getPosition().copy().add(left));
        points.add(vehicle2.getPosition().copy().add(right));

        showLines(points, 0xFF0000FF, 5);
    }

    public void showLines(ArrayList<PVector> points, int color, int size) {

        p.stroke(color);
        p.strokeWeight(size);
        p.noFill();

        p.beginShape();
        for (PVector point : points) {
            p.vertex(point.x, point.y);
        }
        p.vertex(points.get(0).x, points.get(0).y);
        p.endShape();

    }

    public void showPoints(ArrayList<PVector> points, int color, int size) {

        p.stroke(color);
        p.strokeWeight(size);

        for (PVector point : points) {
            p.point(point.x, point.y);
        }

    }

}
