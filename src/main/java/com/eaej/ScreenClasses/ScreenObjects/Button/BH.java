package com.eaej.ScreenClasses.ScreenObjects.Button;

import java.util.ArrayList;

import processing.core.PApplet;

public class BH {
    
    private PApplet p;

    private ArrayList<Button> buttons;

    public String buttonPressed = "";

    public BH(PApplet p) {
        this.p = p;
        buttons = new ArrayList<Button>();
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public void update() {
        for(int i = buttons.size()-1; i >= 0; i--) {
            Button button = buttons.get(i);
            button.update();
            if (button.state == button.CLICKED) {
                buttonPressed = button.id;
            }
        }
    }

    public void render() {
        for (Button button : buttons) {
            button.render(p);
        }
    }

}
