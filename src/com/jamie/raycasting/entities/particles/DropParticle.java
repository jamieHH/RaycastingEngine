package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;

public class DropParticle extends Particle
{
    protected Bitmap[] getTexOptions() {
        return new Bitmap[] {
                Texture.drop
        };
    }

    public DropParticle(int count) {
        super(count, 0.25, 1, 1);
        life = 20;
    }
}