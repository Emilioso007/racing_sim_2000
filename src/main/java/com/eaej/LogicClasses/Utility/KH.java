package com.eaej.LogicClasses.Utility;

import java.util.HashMap;

public class KH {

    public static HashMap<Integer, String> keyNames = new HashMap<Integer, String>();
    public static HashMap<String, Integer> keys = new HashMap<String, Integer>();

    public final static int PRESSED = 2;
    public final static int CLICKED = 1;
    public final static int RELEASED = 0;

    private KH() {

    }

    public static void init() {
        keyNames.put(65, "A");
        keyNames.put(66, "B");
        keyNames.put(67, "C");
        keyNames.put(68, "D");
        keyNames.put(69, "E");
        keyNames.put(70, "F");
        keyNames.put(71, "G");
        keyNames.put(72, "H");
        keyNames.put(73, "I");
        keyNames.put(74, "J");
        keyNames.put(75, "K");
        keyNames.put(76, "L");
        keyNames.put(77, "M");
        keyNames.put(78, "N");
        keyNames.put(79, "O");
        keyNames.put(80, "P");
        keyNames.put(81, "Q");
        keyNames.put(82, "R");
        keyNames.put(83, "S");
        keyNames.put(84, "T");
        keyNames.put(85, "U");
        keyNames.put(86, "V");
        keyNames.put(87, "W");
        keyNames.put(88, "X");
        keyNames.put(89, "Y");
        keyNames.put(90, "Z");
        keyNames.put(32, "SPACE");
        keyNames.put(10, "ENTER");
        keyNames.put(37, "LEFT");
        keyNames.put(38, "UP");
        keyNames.put(39, "RIGHT");
        keyNames.put(40, "DOWN");

    }

    public static void update() {

        for (String key : keys.keySet()) {
            if (keys.get(key) == CLICKED) {
                keys.put(key, PRESSED);
            }
        }

    }

    public static boolean clicked(String string) {
        try {
            return keys.get(string) == CLICKED;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean pressed(String string) {
        try {
            return keys.get(string) == PRESSED;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean released(String string) {
        try {
            return keys.get(string) == RELEASED;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static void simulateKeyPress(String key, int state) {
        keys.put(key, state);
    }
}
