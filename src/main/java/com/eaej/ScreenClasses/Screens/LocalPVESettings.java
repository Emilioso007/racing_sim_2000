package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class LocalPVESettings extends Screen {

    private int aiAmount = 1; // Number of AI vehicles

    // Constructor
    public LocalPVESettings(PApplet p) {
        super(p);

        // Add buttons for difficulty selection and navigation
        buttonHandler.addButton(new Button(100, 100, 200, 100, "Easy", "easy_button"));
        buttonHandler.addButton(new Button(100, 250, 200, 100, "Medium", "medium_button"));
        buttonHandler.addButton(new Button(100, 400, 200, 100, "Hard", "hard_button"));
        buttonHandler.addButton(new Button(100, 550, 200, 100, "Back", "back_button"));

        // Add buttons for adjusting AI amount
        buttonHandler.addButton(new Button(400, 100, 100, 100, "+", "add_button"));
        buttonHandler.addButton(new Button(400, 300, 100, 100, "-", "sub_button"));
    }

    // Update method for the screen
    @Override
    public void update() {
        buttonHandler.update(); // Update button states
        switch (buttonHandler.buttonClicked) {
            case "easy_button":
                ScreenManager.setCurrentScreen(new LocalPVE(p, aiAmount, 1)); // Switch to easy difficulty
                break;

            case "medium_button":
                ScreenManager.setCurrentScreen(new LocalPVE(p, aiAmount, 2)); // Switch to medium difficulty
                break;

            case "hard_button":
                ScreenManager.setCurrentScreen(new LocalPVE(p, aiAmount, 3)); // Switch to hard difficulty
                break;

            case "back_button":
                ScreenManager.setCurrentScreen(new ModeSelect(p)); // Go back to mode selection screen
                break;

            case "add_button":
                aiAmount++; // Increase AI amount
                break;

            case "sub_button":
                if (aiAmount > 1) {
                    aiAmount--; // Decrease AI amount if greater than 1
                }
                break;

            default:
                break;
        }
    }

    // Render method for the screen
    @Override
    public void render() {
        p.background(0); // Set background color to black
        buttonHandler.render(); // Render buttons
        p.fill(255); // Set text color to white
        p.textSize(32); // Set text size
        p.text("AI Amount: " + aiAmount, 450, 250); // Display AI amount
    }
}
