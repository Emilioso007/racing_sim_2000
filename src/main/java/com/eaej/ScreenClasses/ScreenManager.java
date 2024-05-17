package com.eaej.ScreenClasses;

import com.eaej.ScreenClasses.Screens.Screen;

public class ScreenManager {

    // Static variable to hold the current screen
    private static Screen currentScreen;

    // Private constructor to prevent instantiation of ScreenManager objects
    private ScreenManager() {
    }

    // Method to run the current screen by updating and rendering it
    public static void run() {
        currentScreen.update();
        currentScreen.render();
    }

    // Method to set the current screen to the specified screen
    public static void setCurrentScreen(Screen screen) {
        currentScreen = screen;
    }

}
