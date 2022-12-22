package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testing {
    @Test(expected = NullPointerException.class)
    public void testHealth() {
        Tanks tanks = new Tanks(4);

    }

    @Test
    public void testPosition() {
        Projectile projectile = new Projectile(100, 100);
        projectile.update(1);
        assertEquals(200, (int) projectile.getX());
    }
}