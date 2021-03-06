package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;

public class HealthParticle extends Particle
{
    protected Bitmap[] getTexOptions() {
        return new Bitmap[] {
                Texture.health0,
                Texture.health1
        };
    }

    public HealthParticle(int count) {
        super(count, 0.25, -0.125, 0.5);
    }
}