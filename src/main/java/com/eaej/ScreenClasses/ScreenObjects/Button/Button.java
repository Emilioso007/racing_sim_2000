package com.eaej.ScreenClasses.ScreenObjects.Button;

import com.eaej.LogicClasses.Utility.MH;

import processing.core.PApplet;

public class Button {

    public int x, y, w, h;
    public String text, id;

    public final int IDLE = 0;
    public final int HOVERED = 1;
    public final int PRESSED = 2;
    public final int CLICKED = 3;

    public int state = IDLE;

    public Button(int x, int y, int w, int h, String text, String id) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
        this.id = id;
    }

    public void update() {
        if (isClicked()) {
            state = CLICKED;
        } else if (isPressed()) {
            state = PRESSED;
        } else if (isHovered()) {
            state = HOVERED;
        } else {
            state = IDLE;
        }
    }

    public boolean isHovered() {
        int mx = MH.X;
        int my = MH.Y;
        return mx > x && mx < x + w && my > y && my < y + h;
    }

    public boolean isClicked() {
        return isHovered() && MH.LEFT == MH.CLICKED;
    }

    public boolean isPressed() {
        return isHovered() && MH.LEFT == MH.PRESSED;
    }

    public void render(PApplet p) {
        p.noStroke();

        p.pushMatrix();
        p.translate(x, y);

        switch (state) {
            case IDLE:
                p.fill(255);
                p.rect(0, 0, w, h);
                p.fill(0);
                p.textAlign(PApplet.CENTER, PApplet.CENTER);
                p.textSize(16);
                p.text(text, w / 2, h / 2);

                break;

            case HOVERED:
                p.fill(200);
                p.rect(0, 0, w, h);
                p.fill(0);
                p.textAlign(PApplet.CENTER, PApplet.CENTER);
                p.textSize(16);
                p.text(text, w / 2, h / 2);

                break;

            case PRESSED:
                p.fill(150);
                p.rect(0, 0, w, h);
                p.fill(0);
                p.textAlign(PApplet.CENTER, PApplet.CENTER);
                p.textSize(16);
                p.text(text, w / 2, h / 2);

                break;

            case CLICKED:
                p.fill(100);
                p.rect(0, 0, w, h);
                p.fill(0);
                p.textAlign(PApplet.CENTER, PApplet.CENTER);
                p.textSize(16);
                p.text(text, w / 2, h / 2);

                break;

            default:
                break;
        }

        p.popMatrix();
    }

    public void onClick() {
    }

}
