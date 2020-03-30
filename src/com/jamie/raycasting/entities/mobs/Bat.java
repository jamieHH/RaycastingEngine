package com.jamie.raycasting.entities.mobs;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.jamapp.InputHandler;

public class Bat extends Mob
{
    protected Sprite getDefaultSprite() {
        return new Sprite(new Bitmap[] {
                Texture.bat0,
                Texture.bat1,
                Texture.bat2,
                Texture.bat1,
        });
    }

    protected Sprite getActionSprite() {
        return new Sprite(new Bitmap[] {
                Texture.batAtt0,
                Texture.batAtt1,
                Texture.batAtt2,
        });
    }

    protected Sprite getHealSprite() {
        return null;
    }

    protected Sprite getHurtSprite() {
        return new Sprite(new Bitmap[] {
                Texture.batHurt0,
                Texture.batHurt1,
                Texture.batHurt2,
        });
    }

    protected Sprite getDeathSprite() {
        return new Sprite(new Bitmap[] {
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

        viewDist = 8;
        baseReach = 1;

        radius = 0.25;
        rotationSpeed = 0.03;
        walkSpeed = 0.04;

        maxHealth = 3;
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";
    }
}
