package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Bat extends Mob
{
    protected Sprite getSprite() {
        return new Sprite(new Render[] {
                Texture.bat0,
                Texture.bat1,
                Texture.bat2,
                Texture.bat1,
        });
    }

    protected Sprite getActionSprite() {
        return new Sprite(new Render[] {
                Texture.batAtt0,
                Texture.batAtt1,
                Texture.batAtt2,
        });
    }

    protected Sprite getHealSprite() {
        return null;
    }

    protected Sprite getHurtSprite() {
        return new Sprite(new Render[] {
                Texture.batHurt0,
                Texture.batHurt1,
                Texture.batHurt2,
        });
    }

    protected Sprite getDeathSprite() {
        return new Sprite(new Render[] {
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1
        });
    }


    public Bat(InputHandler input) {
        super(input);

        isFloating = true;
        isSolid = true;

        baseReach = 1;
        viewDist = 4;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.03;

        maxHealth = 10;
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";
    }

    public void tick() {
        super.tick();

        input.resetInfluence();
        for (int i = 0; i < level.getMobEntities().size(); i++) {
            if (level.getMobEntities().get(i).getFaction().equals(enemyFaction) && level.getMobEntities().get(i) != this) {
                target = level.getMobEntities().get(i);
                if (!target.isDead) {
                    if (squareDistanceFrom(target.posZ, target.posX) < viewDist) {
                        lookTowards(target.posZ, target.posX);

                        input.forwardInf = 100;
                        input.backInf = 10;
                        input.leftInf = 50;
                        input.rightInf = 50;
                        input.rotLeftInf = 50;
                        input.rotRightInf = 50;
                        input.action = squareDistanceFrom(target.posZ, target.posX) < getRightHandReach();
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
