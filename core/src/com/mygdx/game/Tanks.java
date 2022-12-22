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
        if (tank_choice > 3) {
            try {
                throw new Exception("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        v = new Vector2();
        speed = 45;
        tank1 = new Rectangle();
        tank2 = new Rectangle();

        tank1.x = 50;
        tank1.y = 120;
        tank1.width = 62;
        tank1.height = 62;

        tank2.x = 480;
        tank2.y = 120;
        tank2.width = 62;
        tank2.height = 62;
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
