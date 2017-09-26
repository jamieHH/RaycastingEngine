package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Bat extends Mob
{
    public Bat(InputHandler input) {
        this.input = input;

//        wallCollide = false;
//        entCollide = false;

        useDist = 12;
        viewDist = 64;

        radius = 4;

        rotationSpeed = 0.01;
        walkSpeed = 0.15;
        runSpeed = 0.2;
        crouchSpeed = 0.075;

        camHeightMod = 2.0;
        crouchHeightMod = 4.0;

        maxHealth = 3;
//        maxHealth = 100;
        health = maxHealth;

        Sprite sprite1 = new Sprite(0, 0, 0, Texture.bat0);
        Sprite sprite2 = new Sprite(0, 0, 0, Texture.bat1);
        Sprite sprite3 = new Sprite(0, 0, 0, Texture.bat2);

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
}
