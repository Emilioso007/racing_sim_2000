package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class LocalPVPWinScreen extends Screen {

    private float timeElapsed; 
    private int winningPlayer;

    public LocalPVPWinScreen(PApplet p, float timeElapsed, int winningPlayer) {
        super(p);
        this.winningPlayer = winningPlayer;
        this.timeElapsed = timeElapsed;
        buttonHandler.addButton(new Button(100, 100, 200, 100, "Back", "back_button"));
    }

    @Override
    public void update() {
        buttonHandler.update();
        switch (buttonHandler.buttonClicked) {
            case "back_button":
                ScreenManager.setCurrentScreen(new StartMenu(p));
                break;
        }
    }

    @Override
    public void render() {
        p.background(0);
        p.textSize(32);
        p.fill(255);
        p.text("Player " + winningPlayer + " made it in only " + timeElapsed + " seconds!", p.width / 2, p.height / 2);
        buttonHandler.render();
    }

}
