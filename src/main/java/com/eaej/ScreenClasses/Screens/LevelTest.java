package com.eaej.ScreenClasses.Screens;

import java.util.ArrayList;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.MH;

import processing.core.PApplet;
import processing.core.PVector;

public class LevelTest extends Screen {

    private Level level;

    public LevelTest(PApplet p) {
        super(p);

        level = LevelFactory.createBlobLevel(100, PApplet.min(p.width, p.height) / 2);

    }

    @Override
    public void update() {
        if (MH.LEFT == MH.CLICKED) {
            level = LevelFactory.createBlobLevel(100, PApplet.min(p.width, p.height) / 2);
        }

    }

    @Override
    public void render() {
        p.background(0);

        p.pushMatrix();
        p.translate(p.width / 2, p.height / 2);

        showLines(level.getPoints(), 0xFFFFFFFF, 2);

        showPoints(level.getPoints(), 0xFFFF0000, 5);

        p.popMatrix();
    }

    public void showLines(ArrayList<PVector> points, int color, int size) {

        p.stroke(color);
        p.strokeWeight(size);
        p.noFill();

        p.beginShape();
        for (PVector point : points) {
            p.vertex(point.x, point.y);
        }
        p.vertex(points.get(0).x, points.get(0).y);
        p.endShape();

    }

    public void showPoints(ArrayList<PVector> points, int color, int size) {

        p.stroke(color);
        p.strokeWeight(size);

        for (PVector point : points) {
            p.point(point.x, point.y);
        }

    }

}
