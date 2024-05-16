package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class LocalPVPWinScreen extends Screen {

    private float timeElapsed; // Time elapsed since the game started
    private int winningPlayer; // Player who won the game

    // Constructor
    public LocalPVPWinScreen(PApplet p, float timeElapsed, int winningPlayer) {
        super(p);
        this.winningPlayer = winningPlayer; // Set winning player
        this.timeElapsed = timeElapsed; // Set time elapsed
        buttonHandler.addButton(new Button(100, 100, 200, 100, "Back", "back_button")); // Add back button
    }

    // Update method
    @Override
    public void update() {
        buttonHandler.update(); // Update button states
        switch (buttonHandler.buttonClicked) {
            case "back_button":
                ScreenManager.setCurrentScreen(new StartMenu(p)); // Return to start menu
                break;
        }
    }

    // Render method
    @Override
    public void render() {
        p.background(0); // Set background color to black
        p.textSize(32); // Set text size
        p.fill(255); // Set fill color to white
        // Display winner and elapsed time
        p.text("Player " + winningPlayer + " made it in only " + timeElapsed + " seconds!", p.width / 2, p.height / 2);
        buttonHandler.render(); // Render buttons
    }

}
