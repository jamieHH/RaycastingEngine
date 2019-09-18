package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;

public class BloodParticle extends Particle
{
    protected Bitmap[] getTexOptions() {
        return new Bitmap[] {
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