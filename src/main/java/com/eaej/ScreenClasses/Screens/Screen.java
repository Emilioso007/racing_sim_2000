package com.eaej.ScreenClasses.Screens;

import java.util.ArrayList;

import com.eaej.LogicClasses.Vehicle.Vehicle;
import com.eaej.ScreenClasses.ScreenObjects.Button.ButtonHandler;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Screen {

    // Processing applet instance
    public PApplet p;

    // Debug mode flag
    boolean debug = false;

    // Color for debugging
    int colour;

    // Button handler
    public ButtonHandler buttonHandler;

    // Array of car images
    public PImage[] carImages;

    // Constructor
    public Screen(PApplet p) {
        this.p = p;
        buttonHandler = new ButtonHandler(p);

        // Load car images
        carImages = new PImage[5];
        carImages[0] = p.loadImage("src/main/java/com/eaej/Resources/CarImages/RedCar.png");
        carImages[1] = p.loadImage("src/main/java/com/eaej/Resources/CarImages/GreenCar.png");
        carImages[2] = p.loadImage("src/main/java/com/eaej/Resources/CarImages/PurpleCar.png");
        carImages[3] = p.loadImage("src/main/java/com/eaej/Resources/CarImages/BlueCar.png");
        carImages[4] = p.loadImage("src/main/java/com/eaej/Resources/CarImages/YellowCar.png");
    }

    // Update method (to be overridden)
    public void update() {

    }

    // Render method (to be overridden)
    public void render() {

    }

    // Render method for showing a vehicle
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

    // Overloaded method to show a vehicle in a specific PGraphics context
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

    // Method to render a vehicle with an image
    public void showVehicle(Vehicle vehicle, PImage image) {
        showImage(image, vehicle.getPosition(), vehicle.getHeading().heading() + PApplet.HALF_PI);
    }

    // Overloaded method to render a vehicle with an image in a specific PGraphics
    // context
    public void showVehicle(Vehicle vehicle, PImage image, PGraphics pG) {
        showImage(image, vehicle.getPosition(), vehicle.getHeading().heading() + PApplet.HALF_PI, pG);
    }

    // Method to show an image
    public void showImage(PImage image, PVector position, float angle) {
        p.pushMatrix();
        p.translate(position.x, position.y);
        p.rotate(angle);
        p.image(image, -image.width / 2, -image.height / 2);
        p.popMatrix();
    }

    // Overloaded method to show an image in a specific PGraphics context
    public void showImage(PImage image, PVector position, float angle, PGraphics pG) {
        pG.pushMatrix();
        pG.translate(position.x, position.y);
        pG.rotate(angle);
        pG.image(image, -image.width / 2, -image.height / 2);
        pG.popMatrix();
    }

    // Method to show lines
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

    // Overloaded method to show lines in a specific PGraphics context
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

    // Method to show points
    public void showPoints(ArrayList<PVector> points, int color, int size) {
        p.stroke(color);
        p.strokeWeight(size);

        for (PVector point : points) {
            p.point(point.x, point.y);
        }
    }

    // Method for debugging vehicle behavior
    public void debug(Vehicle vehicle) {
        if (debug) {
            p.stroke(0, 255, 0);
            p.fill(0, 255, 0);
            p.line(vehicle.getPosition().x, vehicle.getPosition().y, vehicle.getFuturePosition().x,
                    vehicle.getFuturePosition().y);
            p.ellipse(vehicle.getFuturePosition().x, vehicle.getFuturePosition().y, 4, 4);

            p.stroke(0, 255, 0);
            p.fill(0, 255, 0);
            p.ellipse(vehicle.getNormal().x, vehicle.getNormal().y, 4, 4);

            p.stroke(0, 255, 0);
            p.line(vehicle.getFuturePosition().x, vehicle.getFuturePosition().y, vehicle.getTarget().x,
                    vehicle.getTarget().y);
            if (vehicle.getWorldRecord() > vehicle.getRadius()) {
                p.fill(255, 0, 0);
            } else {
                p.fill(0, 255, 0);
            }
            p.noStroke();
            p.ellipse(vehicle.getTarget().x, vehicle.getTarget().y, 8, 8);
        }
    }

    // Method to show the track
    public void showTrack(ArrayList<PVector> points) {
        showLines(points, 0xFF3B3B3B, 120);
        showLines(points, 0xFFFFFFFF, 5);
    }

    // Overloaded method to show the track in a specific PGraphics context
    public void showTrack(ArrayList<PVector> points, PGraphics pG) {
        showLines(points, 0xFF3B3B3B, 120, pG);
        showLines(points, 0xFFFFFFFF, 5, pG);
    }
}
