package com.eaej;

import com.eaej.LogicClasses.Utility.MH;
import com.eaej.LogicClasses.Utility.PerlinNoise;
import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.Screens.StartMenu;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Hello world!
 *
 */
public class App extends PApplet {

    public ScreenManager screenManager;

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

        ScreenManager.setCurrentScreen(new StartMenu(this));

        PerlinNoise.init(this);
    }

    public void draw() {
        ScreenManager.run();

        MH.update(mouseX, mouseY);
    }

    public void mousePressed() {

        switch (mouseButton) {
            case PConstants.LEFT:
                MH.LEFT = MH.CLICKED;
                break;
            case PConstants.RIGHT:
                MH.RIGHT = MH.CLICKED;
                break;
            default:
                break;
        }
    }

    public void mouseReleased() {
        switch (mouseButton) {
            case PConstants.LEFT:
                MH.LEFT = MH.RELEASED;
                break;
            case PConstants.RIGHT:
                MH.RIGHT = MH.RELEASED;
                break;
            default:
                break;
        }
    }

}
