package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class WoodParticle extends Particle
{
    public WoodParticle(double x, double z) {
        super(x, z);

        Render[] ts = {
                Texture.splinter0,
                Texture.splinter1,
        };

        SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)], 0, 0.5, 0);

        setIdleSprite(particle);
    }
}