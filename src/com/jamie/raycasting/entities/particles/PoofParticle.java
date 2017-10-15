package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class PoofParticle extends Particle
{
    public PoofParticle(double x, double z) {
        super(x, z);

//        textures.clear();
//        textures.add(Texture.poof0);
//        textures.add(Texture.poof1);
//        int i = random.nextInt(textures.size());
//
//        SpriteParticle tex = new SpriteParticle(textures.get(i), 0, 0.5, 0);
//        tex.gravity = -0.25; // adjust to rise rather tha fall.
//        addSprite(tex);

        Render[] ts = {
                Texture.poof0,
                Texture.poof1,
        };

        SpriteParticle particle = new SpriteParticle(ts[random.nextInt(ts.length)], 0, 0.5, 0);
        particle.gravity = 0;

        addSprite(particle);
    }

    public void tick() {
        super.tick();
        life -= 1;
        if (life <= 0) {
            remove();
        }
        for (int i = 0; i < countSprites(); i++) {
            getSprite(i).tick();
        }
    }
}