package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class ModeSelect extends Screen {

    // Constructor
    public ModeSelect(PApplet p) {
        super(p);

        // Add buttons for different game modes
        buttonHandler.addButton(new Button(100, 100, 200, 100, "Local PVP", "local_pvp_button"));
        buttonHandler.addButton(new Button(100, 300, 200, 100, "Local PVE", "local_pve_button"));
        buttonHandler.addButton(new Button(100, 500, 200, 100, "Back", "back_button"));

    }

    // Update method
    @Override
    public void update() {
        buttonHandler.update(); // Update button states
        switch (buttonHandler.buttonClicked) {
            case "local_pvp_button":
                ScreenManager.setCurrentScreen(new LocalPVP(p)); // Start local PvP mode
                break;

            case "local_pve_button":
                ScreenManager.setCurrentScreen(new LocalPVESettings(p)); // Proceed to local PvE settings
                break;

            case "back_button":
                ScreenManager.setCurrentScreen(new StartMenu(p)); // Return to start menu
                break;

            default:
                break;
        }

    }

    // Render method
    @Override
    public void render() {
        p.background(0); // Set background color to black
        buttonHandler.render(); // Render buttons
    }

}
