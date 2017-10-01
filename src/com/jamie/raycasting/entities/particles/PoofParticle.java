package com.jamie.raycasting.entities.particles;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class PoofParticle extends Particle
{
    public PoofParticle(double x, double z) {
        super(x, z);

        textures.clear();
        textures.add(Texture.poof0);
        textures.add(Texture.poof1);
        int i = random.nextInt(textures.size());

        SpriteParticle tex = new SpriteParticle(0, 0.5, 0, textures.get(i));
        tex.gravity = -0.25; // adjust to rise rather tha fall.
        addSprite(tex);
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