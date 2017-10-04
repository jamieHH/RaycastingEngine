package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.entities.Entity;

public abstract class Particle extends Entity
{
    protected int life = 120;

    public Particle(double x, double z) {
        this.posX = x;
        this.posZ = z;
        solid = false;
    }

    public void tick() {
        super.tick();
        life -= 1;
        if (life <= 0) {
            remove();
        }

        for (int i = 0; i < countSprites(); i++) {
            getSprite(i).tick();
        }
    }
}