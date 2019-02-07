package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.projectiles.FireballProjectile;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

public class Imp extends Mob
{
    private int healTick = 0;
    private int spellCooldown = 0;

    protected Sprite setSprite() {
        return new Sprite(new Render[] {
                Texture.imp0,
                Texture.imp1,
                Texture.imp2,
                Texture.imp1,
        });
    }

    protected Sprite setActionSprite() {
        return new Sprite(new Render[] {
                Texture.impAtt0,
                Texture.impAtt1,
                Texture.impAtt2,
                Texture.impAtt2,
                Texture.impAtt2,
                Texture.impAtt2,
        });
    }

    protected Sprite setHealSprite() {
        return new Sprite(new Render[] {
                Texture.impHeal0,
                Texture.impHeal1,
                Texture.impHeal2,
                Texture.impHeal1,
                Texture.impHeal0,
                Texture.impHeal1,
        });
    }

    protected Sprite setHurtSprite() {
        return new Sprite(new Render[] {
                Texture.impHurt0,
                Texture.impHurt1,
                Texture.impHurt2,
        });
    }

    protected Sprite setDeathSprite() {
        return new Sprite(new Render[] {
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
        });
    }


    public Imp(InputHandler input) {
        super(input);

        isFloating = true;
        isSolid = true;

        baseReach = 1;
        viewDist = 8;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.03;
        runSpeed = 0.03;
        crouchSpeed = 0.03;

        maxHealth = 10;
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";
    }

    public void tick() {
        super.tick();

        if (healTick > 0) {
            healTick--;
        } else {
            healTick = 400;
            modHealth(2);
        }

        input.resetInfluence();
        for (int i = 0; i < level.getMobEntities().size(); i++) {
            if (level.getMobEntities().get(i).getFaction().equals(enemyFaction) && level.getMobEntities().get(i) != this) {
                target = level.getMobEntities().get(i);
                if (!target.isDead) {
                    if (squareDistanceFrom(target.posX, target.posZ) < viewDist) {
                        lookTowards(target.posX, target.posZ);

                        if (spellCooldown > 0) {
                            spellCooldown--;
                        } else {
                            spellCooldown = 300;
                            runSpriteSet("action");
                            double nextX = Math.sin(rotation);
                            double nextZ = Math.cos(rotation);
                            FireballProjectile p = new FireballProjectile(1, 1);
                            p.setRotation(rotation);
                            level.addEntity(p, posX + nextX, posZ + nextZ);
                        }

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
