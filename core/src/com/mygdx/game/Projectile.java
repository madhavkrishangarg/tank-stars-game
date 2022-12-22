package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Movement {
    private static float a = 4;
    private static float b = 4;

    public boolean remove = false;
    public float gravity;
    public Vector2 start_velocity;
    public Vector2 start_point;
    //final Tank game;
    private float x;
    private float y;
    private Texture shot;

    public Projectile(float x, float y) {
        //this.game = game;
        try {
            shot = new Texture("cannon_ball.png");
        } catch (Exception e) {

        }

        this.x = x;
        this.y = y;
        this.gravity = -10;
        this.start_velocity = new Vector2(a, b);
        this.start_point = new Vector2(0, 0);
    }

    public static void ReduceAngle(float t) {
        if (b > 0.5) {
            Projectile.b -= t * b;
            Projectile.a = (float) Math.pow(32 - (b * b), 0.5);
        }
    }

    public static void IncreaseAngle(float t) {
        if (a > 0.5) {
            Projectile.b += t * b;
            Projectile.a = (float) Math.pow(32 - (b * b), 0.5);
        }

    }

    public static float getA() {
        return a;
    }

    public static float getB() {
        return b;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void update(float t) {
        x = start_velocity.x * t + x;
        y = 0.5f * gravity * t * t + start_velocity.y * t + y;
        if (this.y < 130) {
            remove = true;
        }
    }

//    public float getX(float t) {
//        return start_velocity.x * t + start_point.x;
//    }
//
//    public float getY(float t) {
//        return 0.5f * gravity * t * t + start_velocity.y * t + start_point.y;
//    }

    public void render(SpriteBatch batch) {
        batch.draw(shot, x, y, 15, 15);
    }
}
