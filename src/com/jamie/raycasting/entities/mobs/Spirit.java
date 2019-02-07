package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Spirit extends Mob
{
    private int healTick = 0;
    private int dustTick = 0;

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
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";

        Render[] ts = {
                Texture.spirit0,
                Texture.spirit1,
                Texture.spirit2,
                Texture.spirit1,
        };
        setIdleSprite(new Sprite(ts));

        Render[] ts2 = {
                Texture.spiritAtt0,
                Texture.spiritAtt0,
                Texture.spiritAtt0
        };
        setActionSprite(new Sprite(ts2));

        Render[] ts3 = {
                Texture.spiritHurt0,
                Texture.spiritHurt0,
                Texture.spiritHurt0
        };
        setHurtSprite(new Sprite(ts3));

        Render[] ts4 = {
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1
        };
        setDeathSprite(new Sprite(ts4));

        Render[] ts5 = {
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0
        };
        setHealSprite(new Sprite(ts5));
    }

    public void tick() {
        super.tick();

        if (healTick > 0) {
            healTick--;
        } else {
            healTick = 400;
            modHealth(2);
        }

        if (dustTick > 0) {
            dustTick--;
        } else {
            dustTick = 30;
            level.addEntity(new PoofParticle(), posX, posZ);
        }

        input.resetInfluence();
        for (int i = 0; i < level.getMobEntities().size(); i++) {
            if (level.getMobEntities().get(i).getFaction().equals(enemyFaction) && level.getMobEntities().get(i) != this) {
                target = level.getMobEntities().get(i);
                if (!target.isDead) {
                    if (squareDistanceFrom(target.posX, target.posZ) < viewDist) {
                        lookTowards(target.posX, target.posZ);

                        input.forwardInf = 100;
                        input.backInf = 0;
                        input.leftInf = 75;
                        input.rightInf = 0;
                        input.rotLeftInf = 0;
                        input.rotRightInf = 0;
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
