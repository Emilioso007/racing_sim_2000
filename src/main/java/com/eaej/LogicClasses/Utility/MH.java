package com.eaej.LogicClasses.Utility;

public class MH {

    public static int LEFT = 0;
    public static int RIGHT = 0;
    public static int X = 0;
    public static int Y = 0;

    public final static int PRESSED = 2;
    public final static int CLICKED = 1;
    public final static int RELEASED = 0;

    private MH() {

    }

    public static void update(int x, int y) {

        X = x;
        Y = y;

        if (LEFT == CLICKED) {
            LEFT = PRESSED;
        }

        if (RIGHT == CLICKED) {
            RIGHT = PRESSED;
        }

    }
}
