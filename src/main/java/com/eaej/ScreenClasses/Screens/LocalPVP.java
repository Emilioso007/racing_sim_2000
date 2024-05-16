package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Vehicle.Vehicle;
import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class LocalPVP extends Screen {

    private final int PLAYER_AMOUNT = 2; // Number of players

    public static Vehicle[] vehicles; // Array to hold vehicles

    private PGraphics[] pG; // Array to hold separate graphics contexts

    private Level level; // Level object

    private final int FIRST_PERSON = 0; // First-person camera mode
    private final int THIRD_PERSON = 1; // Third-person camera mode
    private int cameraMode = FIRST_PERSON; // Current camera mode

    private long startTime; // Time when the game started
    private long elapsedTime; // Elapsed time since the game started

    // Constructor
    public LocalPVP(PApplet p) {
        super(p);

        // Add back button
        buttonHandler.addButton(new Button(0, 0, 200, 100, "Back", "back_button"));

        // Create level
        level = LevelFactory.createBlobLevel(250, 2000);

        // Initialize arrays
        vehicles = new Vehicle[PLAYER_AMOUNT];
        pG = new PGraphics[PLAYER_AMOUNT];

        // Create separate graphics contexts for each player
        for (int i = 0; i < pG.length; i++) {
            pG[i] = p.createGraphics(p.width / pG.length, p.height);
        }

        // Create vehicles
        vehicles[0] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3, 10);
        vehicles[1] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3, 10);

        // Set player IDs
        vehicles[0].playerID = Vehicle.PLAYER_WASD;
        vehicles[1].playerID = Vehicle.PLAYER_ARROW;

        // Set level for each vehicle
        for (Vehicle v : vehicles) {
            v.setLevel(level);
        }

        // Record start time
        startTime = System.currentTimeMillis();
    }

    // Update method
    @Override
    public void update() {
        buttonHandler.update(); // Update button states
        switch (buttonHandler.buttonClicked) {
            case "back_button":
                ScreenManager.setCurrentScreen(new ModeSelect(p)); // Return to mode select screen
                break;
            default:
                break;
        }

        // Handle key inputs
        if (KH.clicked("C")) {
            cameraMode = (cameraMode + 1) % 2; // Toggle camera mode
        }
        if (KH.clicked("R")) {
            level = LevelFactory.createBlobLevel(100, 2000); // Reset level
            for (Vehicle v : vehicles) {
                v.setLevel(level); // Set level for each vehicle
            }
        }
        if (KH.clicked("P")) {
            debug = !debug; // Toggle debug mode
        }

        // Update vehicles
        for (Vehicle v : vehicles) {
            v.update();
        }

        // Check if any player has hit the checkpoint
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i].hitCheckpoint(level.getCurrentCheckpoint())) {
                if (level.currentCheckpointIndex + 5 < level.points.size()) {
                    level.currentCheckpointIndex += 5;
                    level.setCurrentCheckpoint(level.currentCheckpointIndex); // Set next checkpoint
                } else {
                    ScreenManager.setCurrentScreen(new LocalPVPWinScreen(p, elapsedTime / 1000f, i + 1)); // Go to win
                                                                                                          // screen
                }
            }
        }

        // Update elapsed time
        long currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - startTime;
    }

    // Render method
    @Override
    public void render() {
        p.background(0); // Set background color to black

        float sectionWidth = p.width / vehicles.length; // Width of each section

        // Render each player's view
        for (int i = 0; i < vehicles.length; i++) {

            pG[i].beginDraw(); // Begin drawing on separate graphics context

            pG[i].background(0); // Set background color to black

            Vehicle v = vehicles[i]; // Current vehicle

            pG[i].pushMatrix(); // Push matrix to apply transformations

            // Apply camera transformations based on camera mode
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

            // Render track and vehicles
            showTrack(level.getPoints(), pG[i]);
            for (int j = 0; j < vehicles.length; j++) {
                showVehicle(vehicles[j], carImages[j], pG[i]);
            }

            // Render checkpoint
            pG[i].fill(255, 0, 0);
            pG[i].ellipse(0, 0, 1, 1);
            pG[i].noFill();

            pG[i].noFill();
            pG[i].stroke(0xFF00FF00);
            pG[i].strokeWeight(5);
            pG[i].ellipse(level.getCurrentCheckpoint().x, level.getCurrentCheckpoint().y, 120, 120);

            pG[i].popMatrix(); // Pop matrix to revert transformations

            pG[i].stroke(255);
            pG[i].rect(0, 0, sectionWidth, pG[i].height);
            pG[i].rect(0, 0, sectionWidth * 2, pG[i].height);

            pG[i].endDraw(); // End drawing on separate graphics context
        }

        // Display each player's view
        for (int i = 0; i < vehicles.length; i++) {
            p.image(pG[i], i * sectionWidth, 0);
        }

        timer(); // Display elapsed time

        buttonHandler.render(); // Render buttons
    }

    // Method to get vehicles array
    public static Vehicle[] getVehicles() {
        return vehicles;
    }

    // Method to display elapsed time
    public void timer() {
        p.fill(255);
        p.textSize(32);
        p.text(elapsedTime / 1000f, p.width - 100, 30);
    }
}
