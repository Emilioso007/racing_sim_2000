package com.eaej;

import com.eaej.LogicClasses.Utility.MH;
import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.Screens.StartMenu;

import processing.core.PApplet;
import processing.core.PConstants;

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
        frameRate(60);
        surface.setTitle("Racing Sim 2000");
        //center the window
        surface.setLocation((displayWidth - width) / 2, (displayHeight - height) / 2);

        screenManager = new ScreenManager();
        screenManager.setCurrentScreen(new StartMenu(this));

    }

    public void draw() {
        screenManager.run();

        MH.update(mouseX, mouseY);
    }

    public void mousePressed() {

        switch (mouseButton) {
            case PConstants.LEFT:
                MH.LEFT = 1;
                break;
            case PConstants.RIGHT:
                MH.RIGHT = 1;
                break;
            default:
                break;
        }
    }

    public void mouseReleased() {
        switch (mouseButton) {
            case PConstants.LEFT:
                MH.LEFT = 0;
                break;
            case PConstants.RIGHT:
                MH.RIGHT = 0;
                break;
            default:
                break;
        }
    }

}
