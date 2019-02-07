package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class EmberParticle extends Particle
{
    protected Sprite getSprite() {
        return null;
    }


    public EmberParticle() {
        super();

        Render[] ts = {
                Texture.ember0,
                Texture.ember1,
        };

        List<Sprite> set = new ArrayList<Sprite>();
        for (int i = 0; i < 8; i++) {
            Render[] rts = {
                    ts[random.nextInt(ts.length)],
                    ts[random.nextInt(ts.length)]
            };

            SpriteParticle particle = new SpriteParticle(rts, 2);
            particle.setOffset( 0, 0.5, 0);
            particle.gravity = -0.25;
            set.add(particle);
        }
        setSpriteParticles(set);
    }
}