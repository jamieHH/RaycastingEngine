package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class BloodParticle extends Particle
{
    public BloodParticle(double x, double z) {
        super(x, z);

        textures.clear();
        textures.add(Texture.blood0);
        textures.add(Texture.blood1);
        textures.add(Texture.blood2);
        textures.add(Texture.blood3);
        int i = random.nextInt(textures.size());

        SpriteParticle tex = new SpriteParticle(0, 0.5, 0, textures.get(i));
        addSprite(tex);
    }
}