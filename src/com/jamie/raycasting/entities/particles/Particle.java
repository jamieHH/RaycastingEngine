package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.entities.Entity;

import java.util.Random;

public abstract class Particle extends Entity
{
    protected static final Random random = new Random();
    protected int life = 120;

    public Particle(double x, double z) {
        setPosition(x, z);
        isSolid = false;
    }

    public void tick() {
        super.tick();

        life -= 1;
        if (life <= 0) {
            remove();
        }
    }
}