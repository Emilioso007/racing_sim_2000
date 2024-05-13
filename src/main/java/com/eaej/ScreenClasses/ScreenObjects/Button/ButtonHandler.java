package com.eaej.ScreenClasses.ScreenObjects.Button;

import java.util.ArrayList;

import processing.core.PApplet;

public class ButtonHandler {

    private PApplet p;

    private ArrayList<Button> buttons;

    public String buttonClicked = "";

    public ButtonHandler(PApplet p) {
        this.p = p;
        buttons = new ArrayList<Button>();
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public void update() {
        buttonClicked = "";
        for (Button button : buttons) {
            button.update();
            if (button.isClicked()) {
                buttonClicked = button.id;
            }
        }
    }

    public void render() {
        for (Button button : buttons) {
            button.render(p);
        }
    }

}
