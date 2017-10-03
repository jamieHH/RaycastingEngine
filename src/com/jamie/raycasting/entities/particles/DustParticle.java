package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class DustParticle extends Particle
{
    public DustParticle(double x, double z) {
        super(x, z);

        textures.clear();
        textures.add(Texture.poof0);
        textures.add(Texture.poof1);
        int i = random.nextInt(textures.size());

        SpriteParticle tex = new SpriteParticle(textures.get(i), 0, 0.5, 0);
        tex.gravity = 0;
        addSprite(tex);
    }
}