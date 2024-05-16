package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class LocalPVEWinScreen extends Screen {

    private float timeElapsed; // Time taken to win

    // Constructor
    public LocalPVEWinScreen(PApplet p, float timeElapsed) {
        super(p);
        this.timeElapsed = timeElapsed; // Initialize time taken
        // Add a button to return to the start menu
        buttonHandler.addButton(new Button(100, 100, 200, 100, "Back", "back_button"));
    }

    // Update method for the screen
    @Override
    public void update() {
        buttonHandler.update(); // Update button states
        switch (buttonHandler.buttonClicked) {
            case "back_button":
                ScreenManager.setCurrentScreen(new StartMenu(p)); // Return to the start menu
                break;
        }
    }

    // Render method for the screen
    @Override
    public void render() {
        p.background(0); // Set background color to black
        p.textSize(32); // Set text size
        p.fill(255); // Set text color to white
        p.text("You made it in only " + timeElapsed + " seconds!", p.width / 2, p.height / 2); // Display time taken
        buttonHandler.render(); // Render buttons
    }

}
