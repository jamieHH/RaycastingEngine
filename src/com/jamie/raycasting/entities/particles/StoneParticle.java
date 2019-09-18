package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;

public class StoneParticle extends Particle
{
    protected Bitmap[] getTexOptions() {
        return new Bitmap[] {
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