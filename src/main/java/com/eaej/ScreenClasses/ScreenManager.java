package com.eaej.ScreenClasses;

public class ScreenManager {

    private Screen currentScreen;

    public ScreenManager() {
    }

    public void run() {
        try {
            currentScreen.update();
            currentScreen.render();
        } catch (Exception e) {
            System.out.println("### Error! Screen is null ###");
        }
    }

    public void setCurrentScreen(Screen screen) {
        currentScreen = screen;
    }

}
