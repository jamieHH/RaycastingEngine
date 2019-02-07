package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class WoodParticle extends Particle
{
    protected Sprite getSprite() {
        return null;
    }


    public WoodParticle() {
        super();

        Render[] ts = {
                Texture.splinter0,
                Texture.splinter1,
        };

        List<Sprite> set = new ArrayList<Sprite>();
        for (int i = 0; i < 8; i++) {
            SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)], 2);
            particle.setOffset( 0, 0.5, 0);
            particle.gravity = 1;

            set.add(particle);
        }
        setSpriteParticles(set);
    }
}