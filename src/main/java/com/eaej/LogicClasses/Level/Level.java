package com.eaej.LogicClasses.Level;

import java.util.ArrayList;

import processing.core.PVector;

public class Level {

    public ArrayList<PVector> points = new ArrayList<PVector>();

    public PVector currentCheckpoint;

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

    public PVector getCurrentCheckpoint() {
        return currentCheckpoint;
    }

    public void setCurrentCheckpoint(PVector currentCheckpoint) {
        this.currentCheckpoint = currentCheckpoint;
    }

}
