package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.DustParticle;
import com.jamie.raycasting.entities.PoofParticle;
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

        useDist = 12;
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
        if (isDead || isDieing) {
            camY = -6.0;
            if (isDead) {
                remove();
            }
            return;
        }

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



        if (input.action) {
            activate();
        }

        doMovements();

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

    public void hurt(Mob source, int damage) {
        if (damageTime > 0 || damage <= 0 || isDieing) return;

        yBob -= 6;

        health -= damage;
        damageTime = 30;

        double mx = (posX - source.posX) / 2;
        double mz = (posZ - source.posZ) / 2;
        push(mx, mz);

        for (int i = 0; i < 2 ; i++) {
            DustParticle particle = new DustParticle(posX, posZ);
            particle.level = level;
            level.addEntity(particle);
        }
    }
}
