package com.eaej;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.eaej.LogicClasses.Vehicle.Vehicle;

import processing.core.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    PApplet p;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testGetNormalPoint() {
        PVector a = new PVector(0, 0);
        PVector b = new PVector(0, 10);
        PVector p = new PVector(5, 5);
        PVector normalPoint = Vehicle.getNormalPoint(p, a, b);
        assertEquals(normalPoint, new PVector(0, 5));
    }

    @Test
    public void testSeekBehaviourAtTarget() {
        Vehicle vehicle = new Vehicle(p, 10, 10);
        PVector target = new PVector(10, 10);
        assertEquals(vehicle.seek(target), new PVector(0, 0));
    }

    @Test
    public void testSeekBehaviourNotAtTarget() {
        Vehicle vehicle = new Vehicle(p, 0, 0);
        PVector target = new PVector(10, 10);
        Vehicle.vel = new PVector(0, 0);
        assertEquals(vehicle.seek(target), new PVector(1.767767f, 1.767767f));
    }
}
