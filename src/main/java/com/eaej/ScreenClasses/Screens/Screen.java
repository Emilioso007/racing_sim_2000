package com.eaej.ScreenClasses.Screens;

import java.util.ArrayList;

import com.eaej.LogicClasses.Vehicle.Vehicle;
import com.eaej.ScreenClasses.ScreenObjects.Button.ButtonHandler;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class Screen {

    public PApplet p;

    boolean debug = false;

    int colour;

    public ButtonHandler buttonHandler;

    public Screen(PApplet p) {
        this.p = p;
        buttonHandler = new ButtonHandler(p);
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

        if (debug && vehicle.separate) {
            colour = 0xFFFF0000;
        } else {
            colour = 0xFF0000FF;
        }
        showLines(points, colour, 5);

        if (vehicle.playerID == Vehicle.PLAYER_AI) {
            debug(vehicle);
        }
    }

    public void showVehicle(Vehicle vehicle, PGraphics pG) {

        ArrayList<PVector> points = new ArrayList<PVector>();

        PVector heading = vehicle.getHeading().copy().mult(20);
        PVector left = heading.copy().rotate(PApplet.radians(135));
        PVector right = heading.copy().rotate(PApplet.radians(-135));

        points.add(vehicle.getPosition().copy().add(heading));
        points.add(vehicle.getPosition().copy().add(left));
        points.add(vehicle.getPosition().copy().add(right));

        if (debug && vehicle.separate) {
            colour = 0xFFFF0000;
        } else {
            colour = 0xFF0000FF;
        }
        showLines(points, colour, 5, pG);

        if (vehicle.playerID == Vehicle.PLAYER_AI) {
            debug(vehicle);
        }
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

    public void showLines(ArrayList<PVector> points, int color, int size, PGraphics pG) {

        pG.stroke(color);
        pG.strokeWeight(size);
        pG.noFill();

        pG.beginShape();
        for (PVector point : points) {
            pG.vertex(point.x, point.y);
        }
        pG.vertex(points.get(0).x, points.get(0).y);
        pG.endShape();

    }

    public void showPoints(ArrayList<PVector> points, int color, int size) {

        p.stroke(color);
        p.strokeWeight(size);

        for (PVector point : points) {
            p.point(point.x, point.y);
        }

    }

    public void debug(Vehicle vehicle) {

        if (debug) {
            // Draw predicted future position
            p.stroke(0, 255, 0);
            p.fill(0, 255, 0);
            p.line(vehicle.getPosition().x, vehicle.getPosition().y,
                    vehicle.getFuturePosition().x, vehicle.getFuturePosition().y);
            p.ellipse(vehicle.getFuturePosition().x, vehicle.getFuturePosition().y, 4, 4);

            // Draw normal position
            p.stroke(0, 255, 0);
            p.fill(0, 255, 0);
            p.ellipse(vehicle.getNormal().x, vehicle.getNormal().y, 4, 4);

            // Draw actual target (red if steering towards it)
            p.stroke(0, 255, 0);
            p.line(vehicle.getFuturePosition().x, vehicle.getFuturePosition().y, vehicle.getTarget().x,
                    vehicle.getTarget().y);
            if (vehicle.getWorldRecord() > vehicle.getRadius()) {
                p.fill(255, 0, 0);
            } else {
                p.fill(0, 255, 0); // Fill with default color
            }
            p.noStroke();
            p.ellipse(vehicle.getTarget().x, vehicle.getTarget().y, 8, 8);
        }
    }

}
