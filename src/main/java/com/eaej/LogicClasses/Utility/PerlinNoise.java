package com.eaej.LogicClasses.Utility;

import processing.core.PApplet;

public class PerlinNoise {

    // Reference to the PApplet for accessing processing functions
    private static PApplet p;

    // Private constructor to prevent instantiation
    private PerlinNoise() {
    }

    // Initialize PerlinNoise with a PApplet reference
    public static void init(PApplet p) {
        PerlinNoise.p = p;
    }

    // Get 1D Perlin noise value
    public static float noise(float x) {
        return p.noise(x);
    }

    // Get 2D Perlin noise value
    public static float noise(float x, float y) {
        return p.noise(x, y);
    }

    // Get 3D Perlin noise value
    public static float noise(float x, float y, float z) {
        return p.noise(x, y, z);
    }

    // Set Perlin noise seed
    public static void noiseSeed(int seed) {
        p.noiseSeed(seed);
    }

}
