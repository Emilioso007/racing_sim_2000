package com.eaej.LogicClasses.Vehicle;

import java.util.ArrayList;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Utility.KH;

import processing.core.PVector;

public class Vehicle {

    public PVector pos, vel, acc;

    public float maxSpeed = 5;

    public int playerID = 0;

    public final static int PLAYER_WASD = 0;
    public final static int PLAYER_ARROW = 1;
    public final static int PLAYER_AI = 2;

    public Vehicle(float x, float y) {
        pos = new PVector(x, y);
        vel = new PVector(0, 0);
        acc = new PVector(0, 0);
    }

    public void update() {

        if (playerID == PLAYER_AI) {
            applyForce();
        } else if (playerID == PLAYER_WASD) {
            if (KH.pressed("W")) {
                applyForce(getHeading());
            }
            if (KH.pressed("S")) {
                applyForce(getHeading().mult(-1));
            }
            if (KH.pressed("A")) {
                rotate(-0.1f);
            }
            if (KH.pressed("D")) {
                rotate(0.1f);
            }
        } else if (playerID == PLAYER_ARROW) {
            if (KH.pressed("UP")) {
                applyForce(getHeading());
            }
            if (KH.pressed("DOWN")) {
                applyForce(getHeading().mult(-1));
            }
            if (KH.pressed("LEFT")) {
                rotate(-0.1f);
            }
            if (KH.pressed("RIGHT")) {
                rotate(0.1f);
            }
        }

        vel.add(acc);
        vel.limit(maxSpeed);
        pos.add(vel);
        acc.mult(0);

        // System.out.println(vel.mag());

    }

    public void applyForce(PVector force) {
        acc.add(force);
    }

    public PVector getHeading() {
        return PVector.fromAngle(vel.heading());
    }

    public void rotate(float f) {
        vel.rotate(f);
    }

    public PVector getPosition() {
        return pos;
    }

    // AI STUFF

    PVector normal = new PVector();
    PVector target = new PVector();
    PVector futurePos = new PVector();
    double worldRecord;
    int radius = 10;

    Level level;

    public void setLevel(Level level) {
        this.level = level;
    }

    PVector follow() {
        PVector future = vel.copy();
        future.normalize();
        future.mult(25);
        futurePos = future.add(pos);

        worldRecord = Double.POSITIVE_INFINITY;

        ArrayList<PVector> points = level.getPoints();

        for (int i = 0; i < points.size(); i++) {
            PVector a = points.get(i);
            PVector b = points.get((i + 1) % points.size());
            PVector normalPoint = getNormalPoint(futurePos, a, b);

            PVector dir = PVector.sub(b, a);

            if (normalPoint.x < Math.min(a.x, b.x) || normalPoint.x > Math.max(a.x, b.x)
                    || normalPoint.y < Math.min(a.y, b.y) || normalPoint.y > Math.max(a.y, b.y)) {
                normalPoint = b.copy();
                a = points.get((i + 1) % points.size());
                b = points.get((i + 2) % points.size());
                dir = PVector.sub(b, a);
            }
            float d = PVector.dist(futurePos, normalPoint);
            if (d < worldRecord) {
                worldRecord = d;
                normal = normalPoint;
                dir.normalize();
                dir.mult(25);
                target = normal.copy();
                target.add(dir);
            }
        }

        if (worldRecord > radius) {
            return seek(target);
        } else {
            return new PVector(0, 0);
        }
    }

    PVector seek(PVector target) {
        PVector desired = PVector.sub(target, pos);

        desired.normalize();
        desired.mult(20);

        PVector steer = PVector.sub(desired, vel);
        steer.limit(2.5f);

        return steer;
    }

    void applyForce() {
        PVector force = follow();
        acc.add(force);
    }

    PVector getNormalPoint(PVector p, PVector a, PVector b) {
        PVector ap = PVector.sub(p, a);
        PVector ab = PVector.sub(b, a);
        ab.normalize();
        ab.mult(ap.dot(ab));
        PVector normalPoint = PVector.add(a, ab);
        return normalPoint;
    }

    public PVector getFuturePosition() {
        return futurePos;
    }

    public PVector getNormal() {
        return normal;
    }

    public double getWorldRecord() {
        return worldRecord;
    }

    public int getRadius() {
        return radius;
    }

    public PVector getTarget() {
        return target;
    }
}
