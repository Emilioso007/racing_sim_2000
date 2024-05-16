package com.eaej.LogicClasses.Utility;

public class MH {

    // Variables to store mouse states and position
    public static int LEFT = 0;
    public static int RIGHT = 0;
    public static int X = 0;
    public static int Y = 0;

    // Constants representing mouse states
    public final static int PRESSED = 2;
    public final static int CLICKED = 1;
    public final static int RELEASED = 0;

    // Private constructor to prevent instantiation
    private MH() {
    }

    // Update mouse states and position
    public static void update(int x, int y) {
        // Update mouse position
        X = x;
        Y = y;

        // If the left mouse button was clicked, change its state to pressed
        if (LEFT == CLICKED) {
            LEFT = PRESSED;
        }

        // If the right mouse button was clicked, change its state to pressed
        if (RIGHT == CLICKED) {
            RIGHT = PRESSED;
        }
    }
}
