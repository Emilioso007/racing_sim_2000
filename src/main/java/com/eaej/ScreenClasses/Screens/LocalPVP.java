package com.eaej.ScreenClasses.Screens;

import com.eaej.LogicClasses.Level.Level;
import com.eaej.LogicClasses.Level.LevelFactory;
import com.eaej.LogicClasses.Utility.KH;
import com.eaej.LogicClasses.Vehicle.Vehicle;

import processing.core.PApplet;
import processing.core.PVector;

public class LocalPVP extends Screen {

  public static Vehicle[] vehicles;

  private Level level;

  private final int FIRST_PERSON = 0;
  private final int THIRD_PERSON = 1;
  private int cameraMode = FIRST_PERSON;

  private int timer;

  public LocalPVP(PApplet p) {
    super(p);

    level = LevelFactory.createBlobLevel(100, 2000);

    vehicles = new Vehicle[2];

    vehicles[0] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3);
    vehicles[1] = new Vehicle(p, level.points.get(0).x, level.points.get(0).y, 3);

    vehicles[0].playerID = Vehicle.PLAYER_WASD;
    vehicles[1].playerID = Vehicle.PLAYER_ARROW;

    for (Vehicle v : vehicles) {
      v.setLevel(level);
    }
    timer = 0;
  }

  @Override
  public void update() {
    timer++;

    if (KH.clicked("C")) {
      cameraMode = (cameraMode + 1) % 2;
    }
    if (KH.clicked("R")) {
      level = LevelFactory.createBlobLevel(100, 2000);
      for (Vehicle v : vehicles) {
        v.setLevel(level);
      }
    }

    if (KH.clicked("P")) {
      debug = !debug;
    }

    for (Vehicle v : vehicles) {
      v.update();
    }

  }

  @Override
  public void render() {
    p.background(0);

    float sectionWidth = p.width / vehicles.length;

    for (int i = 0; i < vehicles.length; i++) {
      Vehicle v = vehicles[i];

      p.pushMatrix();
      p.translate(i * sectionWidth, 0);

      if (cameraMode == FIRST_PERSON) {
        p.translate(sectionWidth / 2 - v.getPosition().x, p.height / 2 - v.getPosition().y);
      } else if (cameraMode == THIRD_PERSON) {
        p.translate(sectionWidth / 2, p.height / 2);
        float maxNoise = 0;
        for (PVector point : level.getPoints()) {
          maxNoise = Math.max(maxNoise, point.z);
        }
        float scale = 0.5f * p.height / maxNoise;
        p.scale(scale);
      }

      showLines(level.getPoints(), 0xFFFFFFFF, 5);
      showLines(level.getPoints(), 0xFF3B3B3B, 120);

      for (Vehicle vehicle : vehicles) {
        showVehicle(vehicle);
      }

      p.popMatrix();
      p.rect(0, 0, sectionWidth, p.height);
      p.rect(0, 0, sectionWidth * 2, p.height);

    }

    timer();
  }

  public static Vehicle[] getVehicles() {
    return vehicles;
  }

  public void timer() {
    p.fill(255);
    p.textSize(32);
    p.text(timer / p.frameRate, p.width - 100, 30);
  }
}
