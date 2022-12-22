package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Movement {
    float x;
    float y;

    public abstract float getX();

    public abstract float getY();

    public abstract void update(float t);

    public abstract void render(SpriteBatch batch);
}
