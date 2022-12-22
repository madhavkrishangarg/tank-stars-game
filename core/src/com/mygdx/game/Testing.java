package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testing {
    @Test
    public void testHealth() {
        Tanks tanks = new Tanks(4);
        tanks.setTank1_health(20);
        int health = tanks.getTank1_health();
        assertEquals(80, health); //should pass
    }

    @Test
    public void testPosition() {
        Projectile projectile = new Projectile(100, 100);
        projectile.update(1);
        assertEquals(200, (int) projectile.getX());
    }
}