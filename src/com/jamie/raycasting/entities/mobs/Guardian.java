package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.StoneParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Guardian extends Mob
{
    private boolean woke = false;

    protected Sprite setSprite() {
        return new Sprite(new Render[] {
                Texture.guardian0
        });
    }

    protected Sprite setActionSprite() {
        return null;
    }

    protected Sprite setHealSprite() {
        return null;
    }

    protected Sprite setHurtSprite() {
        return null;
    }

    protected Sprite setDeathSprite() {
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
        runSpeed = 0.03;
        crouchSpeed = 0.03;

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
                    if (squareDistanceFrom(target.posX, target.posZ) < viewDist) {
                        lookTowards(target.posX, target.posZ);
                        if (!woke) {
                            woke = true;
                            level.addEntity(new StoneParticle(), posX, posZ);
                        }

                        input.forwardInf = 100;
                        input.backInf = 0;
                        input.leftInf = 0;
                        input.rightInf = 0;
                        input.rotLeftInf = 0;
                        input.rotRightInf = 0;
                        input.action = squareDistanceFrom(target.posX, target.posZ) < getRightHandReach();
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
