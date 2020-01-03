package com.jamie.raycasting.entities.particles;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;

public class DarkWoodParticle extends Particle
{
    protected Bitmap[] getTexOptions() {
        return new Bitmap[] {
                Texture.splinterDark0,
                Texture.splinterDark1
        };
    }

    public DarkWoodParticle(int count) {
        super(count, 0.5, 1, 0.5);
    }
}