package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.BloodParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Bat extends Mob
{
    public Bat(InputHandler input) {
        super(input);

//        wallCollide = false;
//        entCollide = false;
        isSolid = true;

        baseReach = 1;
        viewDist = 4;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.0125;
        runSpeed = 0.0125;
        crouchSpeed = 0.0125;

        camHeightMod = 0.652;
        crouchHeightMod = 0.5;

        maxHealth = 10;
//        maxHealth = 100;
        health = maxHealth;

        hurtParticle = new BloodParticle(0, 0);

        Render[] ts = {
                Texture.bat0,
                Texture.bat1,
                Texture.bat2,
                Texture.bat1,
        };
        addIdleSprite(new Sprite(ts));

        Render[] ts2 = {
                Texture.batAtt0,
                Texture.batAtt2,
                Texture.batAtt3,
        };
        addActionSprite(new Sprite(ts2));

        Render[] ts3 = {
                Texture.batHurt0,
                Texture.batHurt0,
                Texture.batHurt1,
        };
        addHurtSprite(new Sprite(ts3));

        Render[] ts4 = {
                Texture.splat0,
        };
        addDeathSprite(new Sprite(ts4, 30));
    }

    public void tick() {
        super.tick();

        if (level.player != null) { // stop gap fix
            input.resetInfluence();
            if (squareDistanceFrom(level.player.posX, level.player.posZ) < viewDist) {

                // TODO: this function needs be replaced
                // - because rotation now returns to 0 after 360 degrees
                // - put this function in the input handler and make the mob input turn to face the target
                // - this might be achieved by checking if the rotation of the mob is more or less than the rotation that lookTowards() calculates
                // - improve input handler to target particular mobs!!
                lookTowards(level.player.posX, level.player.posZ);

                input.forwardInf = 100;
                input.backInf = 25;
                input.action = squareDistanceFrom(level.player.posX, level.player.posZ) < getRightHandReach();
            }
        }
    }
}
