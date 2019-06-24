package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Render;
import com.jamie.jamapp.Texture;

public class BloodParticle extends Particle
{
    protected Render[] getTexOptions() {
        return new Render[] {
                Texture.blood0,
                Texture.blood1,
                Texture.blood2,
                Texture.blood3
        };
    }

    public BloodParticle(int count) {
        super(count, 0.5, 1, 0.5);
    }
}