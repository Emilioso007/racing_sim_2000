package com.eaej.ScreenClasses;

import com.eaej.ScreenClasses.Screens.Screen;

public class ScreenManager {

    private static Screen currentScreen;

    private ScreenManager() {
    }

    public static void run() {
        currentScreen.update();
        currentScreen.render();
    }

    public static void setCurrentScreen(Screen screen) {
        currentScreen = screen;
    }

}
