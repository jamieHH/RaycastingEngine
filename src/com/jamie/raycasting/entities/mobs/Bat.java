package com.jamie.raycasting.entities.mobs;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.jamapp.InputHandler;

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

    protected InfluenceKeyframe getIdleInfluence() {
        return new InfluenceKeyframe(20, 50, 50, 50, 50, 50, 50, 0, null);
    }
    protected InfluenceKeyframe getPursuitInfluence() {
        return new InfluenceKeyframe(20, 100, 10, 50, 50, 0, 0, 0, null);
    }
    protected InfluenceKeyframe getAttackInfluence() {
        return new InfluenceKeyframe(10, 100, 50, 50, 50, 0, 0, 100, null);
    }


    public Bat(InputHandler input) {
        super(input);

        isFloating = true;
        isSolid = true;

        viewDist = 4;
        baseReach = 1;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.03;

        maxHealth = 3;
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";
    }
}
