package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Vehicle.Vehicle;

import processing.core.PApplet;
import processing.core.PVector;

public class Sandbox extends Screen {

    // Properties
    Level level;
    Vehicle vehicle;

    // Camera modes
    private final int FIRST_PERSON = 0;
    private final int THIRD_PERSON = 1;
    private int cameraMode = FIRST_PERSON;

    // Constructor
    public Sandbox(PApplet p) {
        super(p);

        // Create a blob level and initialize the vehicle in the center of the screen
        level = LevelFactory.createBlobLevel(100, 2000);
        vehicle = new Vehicle(p, p.width / 2, p.height / 2, 3, 10);
        vehicle.setLevel(level);
    }

    // Update method
    @Override
    public void update() {

        // Camera control
        if (KH.clicked("C")) {
            cameraMode = (cameraMode + 1) % 2; // Toggle camera mode
        }

        // Reset the level
        if (KH.clicked("R")) {
            level = LevelFactory.createBlobLevel(100, 2000);
            vehicle.setLevel(level);
        }

        // Vehicle controls
        if (KH.pressed("W")) {
            vehicle.applyForce(vehicle.getHeading()); // Move forward
        }
        if (KH.pressed("S")) {
            vehicle.applyForce(vehicle.getHeading().mult(-1)); // Move backward
        }
        if (KH.pressed("A")) {
            vehicle.rotate(-0.1f); // Rotate left
        }
        if (KH.pressed("D")) {
            vehicle.rotate(0.1f); // Rotate right
        }

        vehicle.update(); // Update the vehicle's state
    }

    // Render method
    @Override
    public void render() {

        p.background(0); // Set background color to black

        // Translate based on camera mode
        if (cameraMode == FIRST_PERSON) {
            p.translate(p.width / 2 - vehicle.getPosition().x, p.height / 2 - vehicle.getPosition().y);
        } else if (cameraMode == THIRD_PERSON) {
            p.translate(p.width / 2, p.height / 2);
            float maxNoise = 0;
            for (PVector point : level.getPoints()) {
                maxNoise = Math.max(maxNoise, point.z);
            }
            float scale = 0.5f * p.height / maxNoise;
            p.scale(scale);
        }

        // Render the level
        showLines(level.getPoints(), 0xFF3B3B3B, 120); // Render the level outline
        showLines(level.getPoints(), 0xFFFFFFFF, 5); // Render the level boundary

        // Render the vehicle
        showVehicle(vehicle); // Render the vehicle
    }
}
