package com.eaej.ScreenClasses.Screens;

import com.eaej.ScreenClasses.ScreenManager;
import com.eaej.ScreenClasses.ScreenObjects.Button.Button;

import processing.core.PApplet;

public class LocalPVESettings extends Screen {

    private int aiAmount = 1;

    public LocalPVESettings(PApplet p) {
        super(p);

        buttonHandler.addButton(new Button(100, 100, 200, 100, "Easy", "easy_button"));
        buttonHandler.addButton(new Button(100, 250, 200, 100, "Medium", "medium_button"));
        buttonHandler.addButton(new Button(100, 400, 200, 100, "Hard", "hard_button"));
        buttonHandler.addButton(new Button(100, 550, 200, 100, "Back", "back_button"));

        buttonHandler.addButton(new Button(400, 100, 100, 100, "+", "add_button"));
        buttonHandler.addButton(new Button(400, 300, 100, 100, "-", "sub_button"));

    }

    @Override
    public void update() {
        buttonHandler.update();
        switch (buttonHandler.buttonClicked) {
            case "easy_button":
                ScreenManager.setCurrentScreen(new LocalPVE(p, aiAmount, 1));
                break;

            case "medium_button":
                ScreenManager.setCurrentScreen(new LocalPVE(p, aiAmount, 2));
                break;

            case "hard_button":
                ScreenManager.setCurrentScreen(new LocalPVE(p, aiAmount, 3));
                break;

            case "back_button":
                ScreenManager.setCurrentScreen(new ModeSelect(p));
                break;

            case "add_button":
                aiAmount++;
                break;

            case "sub_button":
                if (aiAmount > 1) {
                    aiAmount--;
                }
                break;

            default:
                break;
        }

    }

    @Override
    public void render() {

        p.background(0);
        buttonHandler.render();
        p.fill(255);
        p.textSize(32);
        p.text("AI Amount: " + aiAmount, 450, 250);

    }

}
