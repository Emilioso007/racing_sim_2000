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

    public static Vehicle[] vehicles; // Array to store vehicles

    private Level level; // The level object

    private final int FIRST_PERSON = 0; // First person camera mode
    private final int THIRD_PERSON = 1; // Third person camera mode
    private int cameraMode = FIRST_PERSON; // Initial camera mode

    private long startTime; // Start time for tracking elapsed time
    private long elapsedTime; // Elapsed time since the start

    // Constructor
    public LocalPVE(PApplet p, int aiAmount, int difficulty) {
        super(p);

        // Create a "Back" button
        buttonHandler.addButton(new Button(0, 0, 200, 100, "Back", "back_button"));

        // Create a level
        level = LevelFactory.createBlobLevel(100, 2000);

        // Initialize the array of vehicles
        vehicles = new Vehicle[aiAmount + 1];

        // Create player-controlled vehicle
        vehicles[0] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3, 3);
        vehicles[0].playerID = Vehicle.PLAYER_WASD;

        float maxSpeed, steerMax;

        // Set vehicle parameters based on difficulty
        switch (difficulty) {
            case 1:
                maxSpeed = 3;
                steerMax = 3;
                break;
            case 2:
                maxSpeed = 3.2f;
                steerMax = 10;
                break;
            case 3:
                maxSpeed = 3.5f;
                steerMax = 100;
                break;
            default:
                maxSpeed = 3;
                steerMax = 3;
                break;
        }

        // Create AI-controlled vehicles
        for (int i = 1; i < vehicles.length; i++) {
            vehicles[i] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, maxSpeed, steerMax);
            vehicles[i].playerID = Vehicle.PLAYER_AI;
        }

        // Set level for all vehicles
        for (Vehicle v : vehicles) {
            v.setLevel(level);
        }

        startTime = System.currentTimeMillis(); // Record start time
    }

    // Update method for the screen
    @Override
    public void update() {
        buttonHandler.update(); // Update button states
        switch (buttonHandler.buttonClicked) {
            case "back_button":
                ScreenManager.setCurrentScreen(new LocalPVESettings(p)); // Switch to settings screen
                break;
        }

        // Toggle camera mode with 'C' key
        if (KH.clicked("C")) {
            cameraMode = (cameraMode + 1) % 2;
        }

        // Reset level and vehicles with 'R' key
        if (KH.clicked("R")) {
            level = LevelFactory.createBlobLevel(100, 2000);
            for (Vehicle v : vehicles) {
                v.setLevel(level);
            }
        }

        // Toggle debug mode with 'P' key
        if (KH.clicked("P")) {
            debug = !debug;
        }

        // Update all vehicles
        for (Vehicle v : vehicles) {
            v.update();
        }

        // Check if the player's vehicle hits the checkpoint
        if (vehicles[0].hitCheckpoint(level.getCurrentCheckpoint())) {
            if (level.currentCheckpointIndex + 5 < level.points.size()) {
                level.currentCheckpointIndex += 5;
                level.setCurrentCheckpoint(level.currentCheckpointIndex);
            } else {
                // Switch to win screen if all checkpoints are passed
                ScreenManager.setCurrentScreen(new LocalPVEWinScreen(p, elapsedTime / 1000f));
            }
        }

        // Calculate elapsed time
        long currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - startTime;
    }

    // Render method for the screen
    @Override
    public void render() {
        p.background(0); // Set background color to black
        p.pushMatrix(); // Save the current transformation matrix

        // Translate to the center of the screen
        if (cameraMode == FIRST_PERSON) {
            p.translate(p.width / 2 - vehicles[0].getPosition().x, p.height / 2 - vehicles[0].getPosition().y);
        } else if (cameraMode == THIRD_PERSON) {
            p.translate(p.width / 2, p.height / 2);
            // Scale based on the distance from the center
            float maxNoise = 0;
            for (PVector point : level.getPoints()) {
                maxNoise = Math.max(maxNoise, PApplet.dist(point.x, point.y, p.width / 2, p.height / 2));
            }
            float scale = 0.5f * p.height / maxNoise;
            p.scale(scale);
        }

        // Render the track
        showTrack(level.getPoints());

        // Render all vehicles
        for (int i = 0; i < vehicles.length; i++) {
            showVehicle(vehicles[i], carImages[i == 0 ? 0 : ((i % 4) + 1)]);
        }

        // Render the current checkpoint
        p.noFill();
        p.stroke(0xFF00FF00); // Green color
        p.strokeWeight(5);
        p.ellipse(level.getCurrentCheckpoint().x, level.getCurrentCheckpoint().y, 120, 120);

        p.popMatrix(); // Restore the previous transformation matrix
        timer(); // Display elapsed time
        buttonHandler.render(); // Render buttons
    }

    // Method to display the timer
    public void timer() {
        p.fill(255);
        p.textSize(32);
        p.text(elapsedTime / 1000f, p.width - 100, 30); // Display time in seconds
    }

    // Method to access the array of vehicles
    public static Vehicle[] getVehicles() {
        return vehicles;
    }
}
