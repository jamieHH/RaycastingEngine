package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Render;
import com.jamie.jamapp.Texture;

public class WoodParticle extends Particle
{
    protected Render[] getTexOptions() {
        return new Render[] {
                Texture.splinter0,
                Texture.splinter1
        };
    }

    public WoodParticle(int count) {
        super(count, 0.5, 1, 0.5);
    }
}