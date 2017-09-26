package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class WoodParticle extends Entity {

    public int life = 120;

    public WoodParticle(double x, double z) {
        this.posX = x;
        this.posZ = z;
        solid = false;

        Render addTex = null;
        int i = random.nextInt(2);
        switch (i) {
            case 0:
                addTex = Texture.splinter0;
                break;
            case 1:
                addTex = Texture.splinter1;
                break;
        }

        SpriteParticle tex = new SpriteParticle(0, 0.5, 0, addTex);
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