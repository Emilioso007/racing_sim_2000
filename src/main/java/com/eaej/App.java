package com.eaej;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.Screens.StartMenu;

import processing.core.PApplet;

/**
 * Hello world!
 *
 */
public class App extends PApplet {

    ScreenManager screenManager;

    public static void main(String[] args) {
        PApplet.main("com.eaej.App");
    }

    public void settings() {
        size(1280, 720, P2D);
    }

    public void setup() {
        surface.setTitle("Racing Sim 2000");
        //center the window
        surface.setLocation((displayWidth - width) / 2, (displayHeight - height) / 2);

        screenManager = new ScreenManager();
        screenManager.setCurrentScreen(new StartMenu(this));

    }

    public void draw() {
        screenManager.run();
    }

}
