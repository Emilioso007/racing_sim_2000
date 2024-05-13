package com.eaej.ScreenClasses;

import com.eaej.ScreenClasses.Screens.Screen;

public class ScreenManager {

    private static Screen currentScreen;

    private ScreenManager() {
    }

    public static void run() {
        // try {
            currentScreen.update();
            currentScreen.render();
        // } catch (Exception e) {
        //     System.out.println("### Error! Screen is null ###");
        // }
    }

    public static void setCurrentScreen(Screen screen) {
        currentScreen = screen;
    }

}
