package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class EmberParticle extends Particle
{
    public EmberParticle(double x, double z) {
        super(x, z);

        Render[] ts = {
                Texture.ember0,
                Texture.ember1,
        };

        SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)], 0, 0.5, 0);
        particle.gravity = -0.5;

        setIdleSprite(particle);
    }
}