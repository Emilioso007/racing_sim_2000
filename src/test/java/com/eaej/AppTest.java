package com.eaej;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Vehicle.Vehicle;
import com.eaej.ScreenClasses.Screens.LocalPVE;

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
        assertEquals(vehicle.seek(target), new PVector(2.1213202f, 2.1213202f));
    }

    @Test
    public void testVehicleMoving() {
        Vehicle vehicle = new Vehicle(p, 0, 0);
        vehicle.playerID = Vehicle.PLAYER_WASD;
        vehicle.vel = new PVector(0, 0);
        KH.simulateKeyPress("W");
        vehicle.update();
        assertEquals(vehicle.vel, new PVector(0.05f, 0));
    }

    @Test
    public void testVehicleSlowingDown() {
        Vehicle vehicle = new Vehicle(p, 0, 0);
        vehicle.playerID = Vehicle.PLAYER_WASD;
        vehicle.vel = new PVector(1, 1);
        vehicle.update();

        assertEquals(vehicle.vel, new PVector(0.9646447f, 0.9646447f));
    }

    @Test
    public void testVehicleRotatingWhenMoving() {
        Vehicle vehicle = new Vehicle(p, 0, 0);
        vehicle.playerID = Vehicle.PLAYER_WASD;
        vehicle.vel = new PVector(1, 1);
        KH.simulateKeyPress("D");
        vehicle.update();
        assertEquals(vehicle.vel, new PVector(0.915227f, 1.0116513f));
    }

    @Test
    public void testVehicleRotatingWhenStopped() {
        Vehicle vehicle = new Vehicle(p, 0, 0);
        vehicle.playerID = Vehicle.PLAYER_WASD;
        vehicle.vel = new PVector(0, 0);
        vehicle.update();
        assertEquals(vehicle.pos, new PVector(0, 0));
    }
}
