package com.eaej.ScreenClasses.Screens;

import java.util.ArrayList;

import com.eaej.LogicClasses.Vehicle.Vehicle;

import processing.core.PApplet;
import processing.core.PVector;

public class Screen {

    public PApplet p;

    public Screen(PApplet p) {
        this.p = p;
    }

    public void update() {

    }

    public void render() {

    }

    public void showVehicle(Vehicle vehicle) {

        ArrayList<PVector> points = new ArrayList<PVector>();

        PVector heading = vehicle.getHeading().copy().mult(20);
        PVector left = heading.copy().rotate(PApplet.radians(135));
        PVector right = heading.copy().rotate(PApplet.radians(-135));

        points.add(vehicle.getPosition().copy().add(heading));
        points.add(vehicle.getPosition().copy().add(left));
        points.add(vehicle.getPosition().copy().add(right));

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
