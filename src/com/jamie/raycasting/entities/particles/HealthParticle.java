package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class HealthParticle extends Particle
{
    public HealthParticle(double x, double z) {
        super(x, z);

        Render[] ts = {
                Texture.health0,
                Texture.health1,
        };

        SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)], 0, 0, 0);
        particle.gravity = -0.125;

        setIdleSprite(particle);
    }
}