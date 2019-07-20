package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Texture;

public class PoofParticle extends Particle
{
    protected Render[] getTexOptions() {
        return new Render[] {
                Texture.poof0,
                Texture.poof1,
        };
    }

    public PoofParticle(int count) {
        super(count, 0.25, -0.5, 0.5);
    }
}