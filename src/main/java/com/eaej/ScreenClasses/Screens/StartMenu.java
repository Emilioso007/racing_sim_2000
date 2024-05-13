package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class StartMenu extends Screen {

    public StartMenu(final PApplet p) {
        super(p);
        buttonHandler.addButton(new Button(100, 100, 200, 100, "Start", "start_button"));
        buttonHandler.addButton(new Button(100, 300, 200, 100, "Exit", "exit_button"));
    }

    @Override
    public void update() {
        buttonHandler.update();
        switch (buttonHandler.buttonClicked) {
            case "start_button":
                ScreenManager.setCurrentScreen(new ModeSelect(p));
                break;

            case "exit_button":
                p.exit();
                break;

            default:
                break;
        }
    }

    @Override
    public void render() {
        p.background(0);
        buttonHandler.render();
    }

}
