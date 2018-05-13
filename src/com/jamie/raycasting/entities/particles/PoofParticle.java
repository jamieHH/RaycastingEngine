package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class PoofParticle extends Particle
{
    public PoofParticle(double x, double z) {
        super(x, z);

        Render[] ts = {
                Texture.poof0,
                Texture.poof1,
        };

        List<Sprite> set = new ArrayList<Sprite>();
        for (int i = 0; i < 6; i++) {
            SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)], 0, 0.5, 0);
            particle.gravity = -0.5;

            set.add(particle);
        }
        setSpriteParticles(set);
    }
}