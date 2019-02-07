package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class DropParticle extends Particle
{
    protected Sprite setSprite() {
        return null;
    }


    public DropParticle() {
        super();

        life = 20;

        Render[] ts = {
                Texture.drop
        };

        List<Sprite> set = new ArrayList<Sprite>();
        for (int i = 0; i < 2; i++) {
            SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)]);
            particle.setOffset(0, 1, 0);
            particle.gravity = 1;

            set.add(particle);
        }
        setSpriteParticles(set);
    }
}