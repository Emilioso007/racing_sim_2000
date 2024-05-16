package com.eaej;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.eaej.LogicClasses.Utility.KH;
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
        Vehicle vehicle = new Vehicle(p, 10, 10, 0, 10);
        PVector target = new PVector(10, 10);
        assertEquals(vehicle.seek(target), new PVector(0, 0));
    }

    @Test
    public void testSeekBehaviourNotAtTarget() {
        Vehicle vehicle = new Vehicle(p, 0, 0, 5, 3);
        PVector target = new PVector(10, 5);
        assertEquals(vehicle.seek(target), new PVector(2.6832814f, 1.3416407f));
    }

    @Test
    public void testVehicleMoving() {
        Vehicle vehicle = new Vehicle(p, 0, 0, 1, 10);
        vehicle.playerID = Vehicle.PLAYER_WASD;
        vehicle.vel = new PVector(0, 0);
        KH.simulateKeyPress("W", KH.PRESSED);
        vehicle.update();
        assertEquals(vehicle.vel, new PVector(0.05f, 0));
        KH.simulateKeyPress("W", KH.RELEASED);
    }

    @Test
    public void testVehicleSlowingDown() {
        Vehicle vehicle = new Vehicle(p, 0, 0, 5, 10);
        vehicle.playerID = Vehicle.PLAYER_WASD;
        vehicle.vel = new PVector(1, 1);
        vehicle.update();
        assertEquals(vehicle.vel, new PVector(0.9646447f, 0.9646447f));
    }

    @Test
    public void testVehicleRotatingWhenMoving() {
        Vehicle vehicle = new Vehicle(p, 0, 0, 5, 10);
        vehicle.playerID = Vehicle.PLAYER_WASD;
        vehicle.vel = new PVector(1, 1);
        KH.simulateKeyPress("D", KH.PRESSED);
        vehicle.update();
        assertEquals(vehicle.vel, new PVector(0.92314696f, 1.0044295f));
        KH.simulateKeyPress("D", KH.RELEASED);
    }

    @Test
    public void testVehicleRotatingWhenStopped() {
        Vehicle vehicle = new Vehicle(p, 0, 0, 0, 10);
        vehicle.playerID = Vehicle.PLAYER_WASD;
        vehicle.vel = new PVector(0, 0);
        vehicle.update();
        assertEquals(vehicle.pos, new PVector(0, 0));
    }

    @Test
    public void testSeparateBehaviour() {
        Vehicle[] vehicles;
        vehicles = new Vehicle[1];
        Vehicle vehicle = new Vehicle(p, 1, 1, 5, 10);
        vehicles[0] = new Vehicle(p, 3, 4, 5, 10);
        vehicles[0].playerID = Vehicle.PLAYER_AI;
        vehicle.vel = new PVector(1, 1);
        vehicles[0].vel = new PVector(1, 1);
        vehicle.separate(vehicles);
        assertEquals(vehicle.steer, new PVector(-3.7735007f, -5.160251f));
    }
}
