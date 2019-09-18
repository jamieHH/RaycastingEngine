package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;

public class WoodParticle extends Particle
{
    protected Bitmap[] getTexOptions() {
        return new Bitmap[] {
                Texture.splinter0,
                Texture.splinter1
        };
    }

    public WoodParticle(int count) {
        super(count, 0.5, 1, 0.5);
    }
}