package com.eaej.LogicClasses.Vehicle;

import java.util.ArrayList;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Utility.KH;
import com.eaej.ScreenClasses.Screens.LocalPVE;

import processing.core.*;

public class Vehicle {

    PApplet p;

    public PVector pos;

    public PVector vel;

    public PVector acc;

    public int playerID = 0;

    public static float maxSpeed;

    public boolean separate = false;

    private float c;

    public float steerMax;

    public PVector steer;

    public final static int PLAYER_WASD = 0;
    public final static int PLAYER_ARROW = 1;
    public final static int PLAYER_AI = 2;

    public Vehicle(PApplet p, float x, float y, float maxSpeed, float steerMax) {
        this.p = p;
        pos = new PVector(x, y);
        vel = new PVector(0, 0);
        acc = new PVector(0, 0);
        this.maxSpeed = maxSpeed;
        this.steerMax = steerMax;
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
            if (vel.mag() >= 0.05) {
                if (KH.pressed("A")) {
                    rotate(-0.05f);
                }
                if (KH.pressed("D")) {
                    rotate(0.05f);
                }
            }
            if (!KH.pressed("W") && !KH.pressed("S")) {
                applyFriction();
            }

        } else if (playerID == PLAYER_ARROW) {
            if (KH.pressed("UP")) {
                applyForce(getHeading());
            }
            if (KH.pressed("DOWN")) {
                applyForce(getHeading().mult(-1));
            }
            if (vel.mag() >= 0.05) {
                if (KH.pressed("LEFT")) {
                    rotate(-0.05f);
                }
                if (KH.pressed("RIGHT")) {
                    rotate(0.05f);
                }
            }
            if (!KH.pressed("UP") && !KH.pressed("DOWN")) {
                applyFriction();
            }
        }

        acc.limit(0.05f);
        vel.add(acc);
        vel.limit(maxSpeed);
        pos.add(vel);
        acc.mult(0);
    }

    public void applyForce(PVector force) {
        acc.add(force);
    }

    public void applyFriction() {
        if (vel.mag() < 0.01f || vel.mag() == 0) {
            c = 0;
        } else
            c = 0.05f;

        PVector friction = vel.copy();
        friction.mult(-1);
        friction.normalize();
        friction.mult(c);
        acc.add(friction);
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
        future.mult(30);
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

    public PVector seek(PVector target) {
        PVector desired = PVector.sub(target, pos);

        desired.normalize();
        desired.mult(maxSpeed);
        System.out.println(maxSpeed);
        PVector steer = PVector.sub(desired, vel);
        steer.limit(steerMax);
        System.out.println(steerMax);
        return steer;
    }

    public PVector separate(Vehicle[] vehicles) {
        float desiredSeparation = 80;
        steer = new PVector(0, 0);
        int count = 0;

        for (Vehicle vehicle : vehicles) {
            float d = PVector.dist(pos, vehicle.pos);
            if (d > 0 && d < desiredSeparation) {
                separate = true;
                PVector diff = PVector.sub(pos, vehicle.pos);
                diff.normalize();
                diff.div(d);
                steer.add(diff);
                count++;
            } else
                separate = false;
        }

        if (count > 0) {
            steer.div((float) count);
        }

        if (steer.mag() > 0) {
            steer.normalize();
            steer.mult(maxSpeed);
            steer.sub(vel);
        }
        return steer;
    }

    void applyForce() {
        PVector force = follow();
        acc.add(separate(LocalPVE.getVehicles()).mult(3));
        acc.add(force);
    }

    public static PVector getNormalPoint(PVector p, PVector a, PVector b) {
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

    public boolean hitCheckpoint(PVector currentCheckpoint) {
        System.out.println(PVector.dist(pos, currentCheckpoint));
        return PVector.dist(pos, currentCheckpoint) < 60;
    }
}
