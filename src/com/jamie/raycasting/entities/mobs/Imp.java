package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Imp extends Mob
{
    public Imp(InputHandler input) {
        super(input);

        isFloating = true;
        isSolid = true;

        baseReach = 1;
        viewDist = 4;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.03;
        runSpeed = 0.03;
        crouchSpeed = 0.03;

        maxHealth = 10;
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";

        Render[] ts = {
                Texture.imp0,
                Texture.imp1,
                Texture.imp2,
                Texture.imp1,
        };
        setIdleSprite(new Sprite(ts));

//        Render[] ts2 = {
//                Texture.impAtt0,
//                Texture.impAtt1,
//                Texture.impAtt2,
//        };
//        setActionSprite(new Sprite(ts2));
//
//        Render[] ts3 = {
//                Texture.impHurt0,
//                Texture.impHurt1,
//                Texture.impHurt2,
//        };
//        setHurtSprite(new Sprite(ts3));

        Render[] ts4 = {
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1
        };
        setDeathSprite(new Sprite(ts4));
    }

    public void tick() {
        super.tick();

        input.resetInfluence();
        for (int i = 0; i < level.getMobEntities().size(); i++) {
            if (level.getMobEntities().get(i).getFaction().equals(enemyFaction) && level.getMobEntities().get(i) != this) {
                target = level.getMobEntities().get(i);
                if (!target.isDead) {
                    if (squareDistanceFrom(target.posX, target.posZ) < viewDist) {
                        lookTowards(target.posX, target.posZ);

                        input.forwardInf = 100;
                        input.backInf = 25;
                        input.leftInf = 50;
                        input.rightInf = 50;
                        input.rotLeftInf = 50;
                        input.rotRightInf = 50;
                        input.action = squareDistanceFrom(target.posX, target.posZ) < getRightHandReach();
                    } else {
                        input.forwardInf = 50;
                        input.backInf = 50;
                        input.leftInf = 50;
                        input.rightInf = 50;
                        input.rotLeftInf = 50;
                        input.rotRightInf = 50;
                        input.action = false;
                    }
                } else {
                    input.action = false;
                }
            }
        }
    }
}
