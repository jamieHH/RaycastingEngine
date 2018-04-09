package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class DropParticle extends Particle
{
    public DropParticle(double x, double z) {
        super(x, z);

        life = 20;

        Render t = Texture.drop;

        SpriteParticle particle = new SpriteParticle(t, 0, 1, 0);

        setIdleSprite(particle);
    }
}