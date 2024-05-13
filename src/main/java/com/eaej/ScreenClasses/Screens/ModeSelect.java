package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class ModeSelect extends Screen {

    public ModeSelect(PApplet p) {
        super(p);

        buttonHandler.addButton(new Button(100, 100, 200, 100, "Local PVP", "local_pvp_button"));
        buttonHandler.addButton(new Button(100, 300, 200, 100, "Local PVE", "local_pve_button"));
        buttonHandler.addButton(new Button(100, 500, 200, 100, "Back", "back_button"));

    }

    @Override
    public void update() {
        buttonHandler.update();
        switch (buttonHandler.buttonClicked) {
            case "local_pvp_button":
                ScreenManager.setCurrentScreen(new LocalPVP(p));
                break;

            case "local_pve_button":
                ScreenManager.setCurrentScreen(new LocalPVESettings(p));
                break;

            case "back_button":
                ScreenManager.setCurrentScreen(new StartMenu(p));
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
