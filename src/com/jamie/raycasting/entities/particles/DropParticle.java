package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Texture;

public class DropParticle extends Particle
{
    protected Render[] getTexOptions() {
        return new Render[] {
                Texture.drop
        };
    }

    public DropParticle(int count) {
        super(count, 0.25, 1, 1);
        life = 20;
    }
}