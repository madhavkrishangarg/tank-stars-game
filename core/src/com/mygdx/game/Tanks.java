package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tanks extends Sprite {
    Rectangle tank1;
    Rectangle tank2;

    Texture tankimage1;
    Texture tankimage2;
    int tank1_health = 100;
    int tank2_health = 100;
    public final Texture health_left;
    public final Texture health_right;
    public final Texture angle_left;
    public final Texture angle_right;
    public final Rectangle anglebar_left;
    public final Rectangle anglebar_right;
    public final Rectangle healthbar_left;
    public final Rectangle healthbar_right;

    private Vector2 v;
    private float speed;

    public Tanks(int tank_choice) {
        if (tank_choice == 1) {
            tankimage1 = new Texture(Gdx.files.internal("green_tank.png"));
            tankimage2 = new Texture(Gdx.files.internal("orange_tank.png"));
        }
        if (tank_choice == 2) {
            tankimage1 = new Texture(Gdx.files.internal("yellow_tank.png"));
            tankimage2 = new Texture(Gdx.files.internal("orange_tank.png"));
        }
        if (tank_choice == 3) {
            tankimage1 = new Texture(Gdx.files.internal("blue_tank.png"));
            tankimage2 = new Texture(Gdx.files.internal("orange_tank.png"));
        }

        v = new Vector2();
        speed = 45;
        tank1 = new Rectangle();
        tank2 = new Rectangle();

        tank1.x = 50;
        tank1.y = 120;
        tank1.width = 65;
        tank1.height = 65;

        tank2.x = 480;
        tank2.y = 120;
        tank2.width = 65;
        tank2.height = 65;

        anglebar_left = new Rectangle();
        anglebar_right = new Rectangle();
        healthbar_left = new Rectangle();
        healthbar_right = new Rectangle();
        anglebar_left.x = 40;
        anglebar_left.y = 80;
        anglebar_left.width = 100;
        anglebar_left.height = 50;
        anglebar_right.x = 430;
        anglebar_right.y = 80;
        anglebar_right.width = 100;
        anglebar_right.height = 50;
        angle_left = new Texture("yellow_rect.png");
        angle_right = new Texture("yellow_rect.png");
        health_left = new Texture("green_rect.png");
        health_right = new Texture("green_rect.png");
        healthbar_left.x = 100;
        healthbar_left.y = 380;
        healthbar_left.height = 50;
        healthbar_left.width = 100;

        healthbar_right.x = 420;
        healthbar_right.y = 380;
        healthbar_right.height = 50;
        healthbar_right.width = 100;
    }

    public Rectangle getTank1() {
        return tank1;
    }

    public void setTank1(Rectangle tank1) {
        this.tank1 = tank1;
    }

    public int getTank1_health() {
        return tank1_health;
    }

    public void setTank1_health(int health) {
        this.tank1_health = tank1_health - health;
    }

    public int getTank2_health() {
        return tank2_health;
    }

    public void setTank2_health(int tank2_health) {
        this.tank2_health = tank2_health;
    }

    //@Override
//    public void draw(Rectangle rectangle){
//        update(Gdx.graphics.getDeltaTime());
//        super.draw(rectangle);
//    }
//    public void UpdatePosition(float delta){
//        float angle = (float) Math.atan2(1,1);
//        velocity1.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);
//        Tank1.setPosition(getX() + velocity1.x*delta,getY() + velocity1.y*delta);
//    }
}
