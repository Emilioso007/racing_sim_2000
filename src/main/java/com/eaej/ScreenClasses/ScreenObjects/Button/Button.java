package com.eaej.ScreenClasses.ScreenObjects.Button;

import com.eaej.LogicClasses.Utility.MH; // Importing utility class for mouse input handling

import processing.core.PApplet;

public class Button {

    public int x, y, w, h; // Button position and dimensions
    public String text, id; // Button label and ID

    // Button states
    public final int IDLE = 0;
    public final int HOVERED = 1;
    public final int PRESSED = 2;
    public final int CLICKED = 3;

    public int state = IDLE; // Initial button state

    // Constructor
    public Button(int x, int y, int w, int h, String text, String id) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
        this.id = id;
    }

    // Update button state
    public void update() {
        if (isClicked()) {
            state = CLICKED;
        } else if (isPressed()) {
            state = PRESSED;
        } else if (isHovered()) {
            state = HOVERED;
        } else {
            state = IDLE;
        }
    }

    // Check if the mouse is hovering over the button
    public boolean isHovered() {
        int mx = MH.X;
        int my = MH.Y;
        return mx > x && mx < x + w && my > y && my < y + h;
    }

    // Check if the button is clicked
    public boolean isClicked() {
        return isHovered() && MH.LEFT == MH.CLICKED;
    }

    // Check if the button is pressed
    public boolean isPressed() {
        return isHovered() && MH.LEFT == MH.PRESSED;
    }

    // Render the button
    public void render(PApplet p) {
        p.noStroke(); // No border for the button

        p.pushMatrix(); // Push current transformation matrix
        p.translate(x, y); // Translate to button position

        switch (state) {
            case IDLE:
                p.fill(255); // White color for idle state
                p.rect(0, 0, w, h); // Draw button rectangle
                p.fill(0); // Black color for text
                p.textAlign(PApplet.CENTER, PApplet.CENTER); // Center text alignment
                p.textSize(16); // Text size
                p.text(text, w / 2, h / 2); // Draw text at the center of the button
                break;

            case HOVERED:
                p.fill(200); // Light gray color for hovered state
                p.rect(0, 0, w, h);
                p.fill(0);
                p.textAlign(PApplet.CENTER, PApplet.CENTER);
                p.textSize(16);
                p.text(text, w / 2, h / 2);
                break;

            case PRESSED:
                p.fill(150); // Gray color for pressed state
                p.rect(0, 0, w, h);
                p.fill(0);
                p.textAlign(PApplet.CENTER, PApplet.CENTER);
                p.textSize(16);
                p.text(text, w / 2, h / 2);
                break;

            case CLICKED:
                p.fill(100); // Dark gray color for clicked state
                p.rect(0, 0, w, h);
                p.fill(0);
                p.textAlign(PApplet.CENTER, PApplet.CENTER);
                p.textSize(16);
                p.text(text, w / 2, h / 2);
                break;

            default:
                break;
        }

        p.popMatrix(); // Restore previous transformation matrix
    }

}
