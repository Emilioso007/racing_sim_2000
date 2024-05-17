package com.eaej.ScreenClasses.Screens;

import java.util.ArrayList;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.MH;

import processing.core.PApplet;
import processing.core.PVector;

public class LevelTest extends Screen {

    private Level level; // The level object to be displayed

    // Constructor
    public LevelTest(PApplet p) {
        super(p); // Call the constructor of the superclass

        // Initialize the level with a blob shape
        level = LevelFactory.createBlobLevel(100, PApplet.min(p.width, p.height) / 2);
    }

    // Update method for the screen
    @Override
    public void update() {
        // Recreate the level when left mouse button is clicked
        if (MH.LEFT == MH.CLICKED) {
            level = LevelFactory.createBlobLevel(100, PApplet.min(p.width, p.height) / 2);
        }
    }

    // Render method for the screen
    @Override
    public void render() {
        p.background(0); // Set background color to black

        p.pushMatrix(); // Save the current transformation matrix
        p.translate(p.width / 2, p.height / 2); // Translate to the center of the screen

        // Display the lines of the level
        showLines(level.getPoints(), 0xFFFFFFFF, 2);

        // Display the points of the level
        showPoints(level.getPoints(), 0xFFFF0000, 5);

        p.popMatrix(); // Restore the previous transformation matrix
    }

    // Method to display lines between points
    public void showLines(ArrayList<PVector> points, int color, int size) {
        p.stroke(color); // Set the stroke color
        p.strokeWeight(size); // Set the stroke weight
        p.noFill(); // Disable filling shapes

        p.beginShape(); // Begin drawing the shape
        for (PVector point : points) {
            p.vertex(point.x, point.y); // Add vertex to the shape
        }
        p.vertex(points.get(0).x, points.get(0).y); // Close the shape
        p.endShape(); // End drawing the shape
    }

    // Method to display points
    public void showPoints(ArrayList<PVector> points, int color, int size) {
        p.stroke(color); // Set the stroke color
        p.strokeWeight(size); // Set the stroke weight

        // Display each point as a small dot
        for (PVector point : points) {
            p.point(point.x, point.y);
        }
    }
}
