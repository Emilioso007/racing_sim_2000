package com.eaej.LogicClasses.LevelCreation;

import java.util.ArrayList;

import processing.core.PVector;

public class Level {

    private ArrayList<PVector> points = new ArrayList<PVector>();

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
