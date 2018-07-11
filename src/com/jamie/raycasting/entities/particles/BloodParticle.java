package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class BloodParticle extends Particle
{
    public BloodParticle() {
        super();

        Render[] ts = {
                Texture.blood0,
                Texture.blood1,
                Texture.blood2,
                Texture.blood3,
        };

        List<Sprite> set = new ArrayList<Sprite>();
        for (int i = 0; i < 2; i++) {
            SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)]);
            particle.setOffset( 0, 0.5, 0);
            particle.gravity = 1;

            set.add(particle);
        }
        setSpriteParticles(set);
    }
}