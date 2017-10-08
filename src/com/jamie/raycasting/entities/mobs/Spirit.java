package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.DustParticle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Render;
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

        rotationSpeed = 0.03;
        walkSpeed = 0.2;
        runSpeed = 0.2;
        crouchSpeed = 0.075;

        camHeightMod = 2.0;
        crouchHeightMod = 4.0;

        maxHealth = 10;
//        maxHealth = 100;
        health = maxHealth;

        hurtParticle = new DustParticle(0, 0);
        hurtParticleCount = 2;

        Render[] ts = {
                Texture.spirit0,
                Texture.spirit1,
                Texture.spirit2,
                Texture.spirit1,
        };

        addIdleSprite(new Sprite(ts));

        Render[] ts2 = {
                Texture.grave,
                Texture.drip3,
        };

        addActionSprite(new Sprite(ts2));

        Render[] ts3 = {
                Texture.grass,
                Texture.leaves,
        };

        addHurtSprite(new Sprite(ts3));

        Render[] ts4 = {
                Texture.splat0,
                Texture.splat1,
                Texture.splat2,
        };

        addDeathSprite(new Sprite(ts4, 10));
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
                input.forwardInf = 100;
                input.backInf = 0;
                input.leftInf = 75;
                input.rightInf = 0;
                if (squareDistanceFrom(level.player.posX, level.player.posZ) < useDist) {
                    input.forwardInf = 50;
                    input.backInf = 50;
                    input.action = true;
                } else {
                    input.action = false;
                }
            }
        }
    }
}
