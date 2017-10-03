package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class WoodParticle extends Particle
{
    public WoodParticle(double x, double z) {
        super(x, z);

        textures.clear();
        textures.add(Texture.splinter0);
        textures.add(Texture.splinter1);
        int i = random.nextInt(textures.size());

        SpriteParticle tex = new SpriteParticle(textures.get(i), 0, 0.5, 0);
        addSprite(tex);
    }
}