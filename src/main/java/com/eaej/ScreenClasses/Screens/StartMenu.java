package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Utility.MH;
import com.eaej.ScreenClasses.Screen;

import processing.core.PApplet;
import processing.core.PConstants;

public class StartMenu extends Screen{

    int i = 0;

    public StartMenu(PApplet p) {
        super(p);
    }

    public void update() {
        i++;
        if(MH.X > 500 && MH.LEFT == MH.CLICKED) {
            System.out.println(i);
        }
    }

    public void render() {
        p.background(0);
        p.textSize(32);
        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.fill(255);
        p.text("Click anywhere to start", p.width/2, p.height/2);
    }
    
}
