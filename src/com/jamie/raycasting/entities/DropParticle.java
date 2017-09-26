package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class DropParticle extends Entity
{
    public int life = 20;

    public DropParticle(double x, double z) {
        this.posX = x;
        this.posZ = z;
        solid = false;

        Render addTex = Texture.drop;

        SpriteParticle tex = new SpriteParticle(0, 1, 0, addTex);
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