package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Spirit extends Mob
{
    private int healTick = 0;

    public Spirit(InputHandler input) {
        super(input);

        isFloating = true;
        isSolid = false;

        baseReach = 1;
        viewDist = 4;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.03;
        runSpeed = 0.03;
        crouchSpeed = 0.03;

        maxHealth = 10;
//        maxHealth = 100;

        Render[] ts = {
                Texture.spirit0,
                Texture.spirit1,
                Texture.spirit2,
                Texture.spirit1,
        };
        setIdleSprite(new Sprite(ts));

        Render[] ts2 = {
                Texture.spiritAtt0,
        };
        setActionSprite(new Sprite(ts2, 15));

        Render[] ts3 = {
                Texture.spiritHurt0,
        };
        setHurtSprite(new Sprite(ts3, 15));

        Render[] ts4 = {
                Texture.splat1,
        };
        setDeathSprite(new Sprite(ts4, 30));

        Render[] ts5 = {
                Texture.spiritHeal0,
        };
        setHealSprite(new Sprite(ts5, 30));

        addFaction("beast");
    }

    public void tick() {
        super.tick();
        input.resetInfluence();

        if (healTick > 0) {
            healTick--;
        } else {
            healTick = 400;
            modHealth(2);
        }



        for (int i = 0; i < level.getMobEntities().size(); i++) {
            if (level.getMobEntities().get(i).getFactions().contains("human")) {
                target = level.getMobEntities().get(i);

                if (squareDistanceFrom(target.posX, target.posZ) < viewDist) {
                    lookTowards(target.posX, target.posZ);

                    input.forwardInf = 100;
                    input.backInf = 0;
                    input.leftInf = 75;
                    input.rightInf = 0;
                    input.action = squareDistanceFrom(target.posX, target.posZ) < getRightHandReach();
                }
            }
        }
    }
}
