package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Utility.MH;
import com.eaej.ScreenClasses.ScreenManager;

import processing.core.PApplet;
import processing.core.PConstants;

public class StartMenu extends Screen {

    public StartMenu(PApplet p) {
        super(p);
    }

    @Override
    public void update() {
        if (MH.LEFT == MH.CLICKED || KH.clicked("ENTER")) {
            ScreenManager.setCurrentScreen(new Sandbox(p));
        }
    }

    @Override
    public void render() {
        p.background(0);
        p.textSize(32);
        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.fill(255);
        p.text("Click anywhere to start", p.width / 2, p.height / 2);
    }

}
