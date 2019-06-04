package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Texture;

public class EmberParticle extends Particle
{
    protected Render[] getTexOptions() {
        return new Render[] {
                Texture.ember0,
                Texture.ember1,
        };
    }

    public EmberParticle(int count) {
        super(count, 0.5, -0.25, 0.5);
    }
}