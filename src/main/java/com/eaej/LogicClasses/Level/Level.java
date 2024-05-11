package com.eaej.LogicClasses.Level;

import java.util.ArrayList;

import processing.core.PVector;

public class Level {

    public ArrayList<PVector> points = new ArrayList<PVector>();

    public Level() {

    }

    public Level(ArrayList<PVector> points) {
        this.points = points;
    }

    public void addPoint(PVector point) {
        points.add(point);
    }

    public ArrayList<PVector> getPoints() {
        return points;
    }

}
