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
        isSolid = false;

        baseReach = 1;
        viewDist = 4;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.0125;
        runSpeed = 0.0125;
        crouchSpeed = 0.0125;

        camHeightMod = 0.625;
        crouchHeightMod = 0.5;

        maxHealth = 10;
//        maxHealth = 100;
        health = maxHealth;

        hurtParticle = new DustParticle(0, 0);

        Render[] ts = {
                Texture.spirit0,
                Texture.spirit1,
                Texture.spirit2,
                Texture.spirit1,
        };
        addIdleSprite(new Sprite(ts));

        Render[] ts2 = {
                Texture.spiritAtt0,
        };
        addActionSprite(new Sprite(ts2, 15));

        Render[] ts3 = {
                Texture.spiritHurt0,
        };
        addHurtSprite(new Sprite(ts3, 15));

        Render[] ts4 = {
                Texture.splat1,
        };
        addDeathSprite(new Sprite(ts4, 30));
    }

    public void tick() {
        super.tick();

        if (poofTick > 0) {
            poofTick--;
        } else {
            poofTick = 20;

            for (int i = 0; i < 1 ; i++) {
                PoofParticle particle = new PoofParticle(posX, posZ);
                level.addEntity(particle);
            }
        }

        if (level.player != null) { // stop gap fix
            input.resetInfluence();
            if (squareDistanceFrom(level.player.posX, level.player.posZ) < viewDist) {
                lookTowards(level.player.posX, level.player.posZ);
                input.forwardInf = 100;
                input.backInf = 0;
                input.leftInf = 75;
                input.rightInf = 0;
                input.action = squareDistanceFrom(level.player.posX, level.player.posZ) < getRightHandReach();
            }
        }
    }
}
