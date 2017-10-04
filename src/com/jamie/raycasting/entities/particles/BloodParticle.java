package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class BloodParticle extends Particle
{
    public BloodParticle(double x, double z) {
        super(x, z);

        Render[] ts = {
                Texture.blood0,
                Texture.blood1,
                Texture.blood2,
                Texture.blood3,
        };

        SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)], 0, 0.5, 0);

        addSprite(particle);
    }
}