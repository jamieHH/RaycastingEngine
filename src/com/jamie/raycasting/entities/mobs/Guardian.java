package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Guardian extends Mob
{
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

    protected List<InfluenceKeyframe> getInfluenceKeyframes() {
        return new ArrayList<InfluenceKeyframe>(Arrays.asList(
                new InfluenceKeyframe(4, 20, 100, 0, 0, 0, 0, null),
                new InfluenceKeyframe(1, 10, 100, 0, 0, 0, 100, null)
        ));
    }

    protected InfluenceKeyframe getIdleInfluence() {
        return new InfluenceKeyframe(0, 20, 0, 0, 0, 0, 0, null);
    }

    public Guardian(InputHandler input) {
        super(input);

        isFloating = false;
        isSolid = true;

        viewDist = 4;
        baseReach = 1;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.03;

        maxHealth = 20;
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";
    }
}
