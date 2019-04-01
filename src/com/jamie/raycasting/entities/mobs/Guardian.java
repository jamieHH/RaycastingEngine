package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.StoneParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Guardian extends Mob
{
    private boolean woke = false;

    protected Sprite getSprite() {
        return new Sprite(new Render[] {
                Texture.guardian0
        });
    }

    protected Sprite getActionSprite() {
        return null;
    }

    protected Sprite getHealSprite() {
        return null;
    }

    protected Sprite getHurtSprite() {
        return null;
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


    public Guardian(InputHandler input) {
        super(input);

        isFloating = true;
        isSolid = true;

        baseReach = 1;
        viewDist = 4;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.03;

        maxHealth = 20;
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
                        if (!woke) {
                            woke = true;
                            level.addEntity(new StoneParticle(), posZ, posX);
                        }

                        input.forwardInf = 100;
                        input.backInf = 0;
                        input.leftInf = 0;
                        input.rightInf = 0;
                        input.rotLeftInf = 0;
                        input.rotRightInf = 0;
                        input.action = squareDistanceFrom(target.posZ, target.posX) < getRightHandReach();
                    } else {
                        input.forwardInf = 0;
                        input.backInf = 0;
                        input.leftInf = 0;
                        input.rightInf = 0;
                        input.rotLeftInf = 0;
                        input.rotRightInf = 0;
                        input.action = false;
                        woke = false;
                    }
                } else {
                    input.action = false;
                }
            }
        }
    }
}
