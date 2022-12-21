package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Movement {
    public float getX();
    public float getY();
    public void update(float x);
    public void render(SpriteBatch batch);
}
