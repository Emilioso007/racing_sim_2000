package com.eaej.ScreenClasses.ScreenObjects.Button;

import java.util.ArrayList;

import processing.core.PApplet;

public class ButtonHandler {

    private PApplet p; // Reference to the PApplet

    private ArrayList<Button> buttons; // ArrayList to store buttons

    public String buttonClicked = ""; // ID of the clicked button

    // Constructor
    public ButtonHandler(PApplet p) {
        this.p = p;
        buttons = new ArrayList<Button>(); // Initialize ArrayList
    }

    // Add a button to the handler
    public void addButton(Button button) {
        buttons.add(button);
    }

    // Update button states
    public void update() {
        buttonClicked = ""; // Reset clicked button ID
        for (Button button : buttons) {
            button.update(); // Update button state
            if (button.isClicked()) { // Check if the button is clicked
                buttonClicked = button.id; // Set the clicked button ID
            }
        }
    }

    // Render all buttons
    public void render() {
        for (Button button : buttons) {
            button.render(p); // Render each button
        }
    }

}
