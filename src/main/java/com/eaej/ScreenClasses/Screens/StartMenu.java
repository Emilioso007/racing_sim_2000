package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.BH;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class StartMenu extends Screen {

    private BH bh;

    public StartMenu(final PApplet p) {
        super(p);
        bh = new BH(p);
        bh.addButton(new Button(100, 100, 200, 100, "Start", "start_button"));
        bh.addButton(new Button(100, 300, 200, 100, "Exit", "exit_button"));
    }

    @Override
    public void update() {
        bh.update();
        switch (bh.buttonPressed) {
            case "start_button":
                ScreenManager.setCurrentScreen(new LocalPVP(p));
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
        bh.render();
    }

}
