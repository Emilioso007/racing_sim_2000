package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class StartMenu extends Screen {

    // Constructor to initialize the StartMenu screen
    public StartMenu(final PApplet p) {
        super(p);

        // Add buttons to the button handler
        buttonHandler.addButton(new Button(100, 100, 200, 100, "Start", "start_button"));
        buttonHandler.addButton(new Button(100, 300, 200, 100, "Exit", "exit_button"));
    }

    // Update method to handle user input and screen transition
    @Override
    public void update() {
        buttonHandler.update();
        switch (buttonHandler.buttonClicked) {
            case "start_button":
                // Switch to the ModeSelect screen when the start button is clicked
                ScreenManager.setCurrentScreen(new ModeSelect(p));
                break;

            case "exit_button":
                // Exit the application when the exit button is clicked
                p.exit();
                break;

            default:
                break;
        }
    }

    // Render method to draw the StartMenu screen
    @Override
    public void render() {
        p.background(0);
        buttonHandler.render();
    }

}
