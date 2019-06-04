package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Texture;

public class StoneParticle extends Particle
{
    protected Render[] getTexOptions() {
        return new Render[] {
                Texture.stone0,
                Texture.stone1,
                Texture.stone2,
                Texture.stone3
        };
    }

    public StoneParticle(int count) {
        super(count, 0.25, 1, 0.5);
    }
}