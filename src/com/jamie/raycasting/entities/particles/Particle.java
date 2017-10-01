package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Render;

import java.util.ArrayList;
import java.util.List;

public abstract class Particle extends Entity
{
    protected int life = 120;

    protected List<Render> textures = new ArrayList<Render>();

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