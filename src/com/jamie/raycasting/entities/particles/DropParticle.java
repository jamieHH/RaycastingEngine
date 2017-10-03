package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class DropParticle extends Particle
{
    public DropParticle(double x, double z) {
        super(x, z);

        life = 20;

        textures.clear();
        textures.add(Texture.drop);
        int i = random.nextInt(textures.size());

        SpriteParticle tex = new SpriteParticle(textures.get(i), 0, 1, 0);
        addSprite(tex);
    }
}