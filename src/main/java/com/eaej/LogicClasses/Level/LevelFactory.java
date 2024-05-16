package com.eaej.LogicClasses.Level;

import java.util.ArrayList;

import com.eaej.LogicClasses.Utility.PerlinNoise;

import processing.core.PApplet;
import processing.core.PVector;

public class LevelFactory {

    // Creates a square-shaped level with hardcoded points
    public static Level createSquareLevel() {
        ArrayList<PVector> points = new ArrayList<PVector>();
        points.add(new PVector(100, 100));
        points.add(new PVector(200, 100));
        points.add(new PVector(200, 200));
        points.add(new PVector(100, 200));
        Level level = new Level(points);
        return level;
    }

    // Creates a circular-shaped level with points calculated using trigonometry
    public static Level createCircleLevel() {
        ArrayList<PVector> points = new ArrayList<PVector>();
        for (int i = 0; i < 360; i++) {
            float x = 100 + 50 * PApplet.cos(PApplet.radians(i));
            float y = 100 + 50 * PApplet.sin(PApplet.radians(i));
            points.add(new PVector(x, y));
        }
        Level level = new Level(points);
        return level;
    }

    // Creates a blob-shaped level with Perlin noise
    // Takes a noise seed to generate consistent noise patterns
    public static Level createBlobLevel(int numPoints, int radius, int noiseSeed) {

        PerlinNoise.noiseSeed(noiseSeed);

        ArrayList<PVector> points = new ArrayList<PVector>();
        for (int i = 0; i < numPoints; i++) {
            float angle = PApplet.radians(i * 360 / numPoints);
            PVector point = PVector.fromAngle(angle);

            float n = PerlinNoise.noise(point.x + 128, point.y + 128) * radius;
            point.mult(n);

            points.add(point);
        }
        Level level = new Level(points);
        level.setCurrentCheckpoint(0);
        return level;

    }

    // Overloaded method to create a blob-shaped level with a random noise seed
    public static Level createBlobLevel(int numPoints, int radius) {
        int seed = (int) (Math.random() * 1000);
        System.out.println("Seed used: " + seed);
        return createBlobLevel(numPoints, radius, seed);
    }

}
