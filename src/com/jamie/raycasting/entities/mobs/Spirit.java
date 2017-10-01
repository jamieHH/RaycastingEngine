package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.DustParticle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Spirit extends Mob
{
    private int poofTick = 0;

    public Spirit(InputHandler input) {
        super(input);

        wallCollide = false;
        entCollide = false;

        useDist = 24;
        viewDist = 256;

        radius = 4;

        rotationSpeed = 0.01;
        walkSpeed = 0.15;
        runSpeed = 0.2;
        crouchSpeed = 0.075;

        camHeightMod = 2.0;
        crouchHeightMod = 4.0;

        maxHealth = 100;
//        maxHealth = 100;
        health = maxHealth;

        hurtParticle = new DustParticle(0, 0);
        hurtParticleCount = 2;

        Sprite sprite1 = new Sprite(0, 0, 0, Texture.spirit0);
        Sprite sprite2 = new Sprite(0, 0, 0, Texture.spirit1);
        Sprite sprite3 = new Sprite(0, 0, 0, Texture.spirit2);

        addSprite(sprite1);
        addSprite(sprite2);
        addSprite(sprite3);
        addSprite(sprite2);
    }

    public void tick() {
        super.tick();

        if (poofTick > 0) {
            poofTick--;
        } else {
            poofTick = 20;

            for (int i = 0; i < 1 ; i++) {
                PoofParticle particle = new PoofParticle(posX, posZ);
                particle.level = level;
                level.addEntity(particle);
            }
        }

        if (level.player != null) { // stop gap fix
            if (squareDistanceFrom(level.player.posX, level.player.posZ) < viewDist) {
                lookTowards(level.player.posX, level.player.posZ);
                input.forward = true;
                if (squareDistanceFrom(level.player.posX, level.player.posZ) < useDist) {
                    input.action = true;
                } else {
                    input.action = false;
                }
            }
        }
    }
}
