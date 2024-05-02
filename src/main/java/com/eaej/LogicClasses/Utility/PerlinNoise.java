package com.eaej.LogicClasses.Utility;

import processing.core.PApplet;

public class PerlinNoise {

    private static PApplet p;

    private PerlinNoise() {

    }

    public static void init(PApplet p) {
        PerlinNoise.p = p;
    }

    public static float noise(float x) {
        return p.noise(x);
    }

    public static float noise(float x, float y) {
        return p.noise(x, y);
    }

    public static float noise(float x, float y, float z) {
        return p.noise(x, y, z);
    }

    public static void noiseSeed(int seed) {
        p.noiseSeed(seed);
    }

}
