package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spirit extends Mob
{
    protected Sprite getSprite() {
        return new Sprite(new Render[] {
                Texture.spirit0,
                Texture.spirit1,
                Texture.spirit2,
                Texture.spirit1,
        });
    }

    protected Sprite getActionSprite() {
        return new Sprite(new Render[] {
                Texture.spiritAtt0,
                Texture.spiritAtt0,
                Texture.spiritAtt0
        });
    }

    protected Sprite getHealSprite() {
        return new Sprite(new Render[] {
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0
        });
    }

    protected Sprite getHurtSprite() {
        return new Sprite(new Render[] {
                Texture.spiritHurt0,
                Texture.spiritHurt0,
                Texture.spiritHurt0
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

    protected Particle getHurtParticle() {
        return new PoofParticle();
    }

    protected List<InfluenceKeyframe> getInfluenceKeyframes() {
        return new ArrayList<InfluenceKeyframe>(Arrays.asList(
                new InfluenceKeyframe(4, 20, 100, 0, 75, 0, 0, null),
                new InfluenceKeyframe(1, 10, 100, 0, 0, 0, 100, null)
        ));
    }

    protected InfluenceKeyframe getIdleInfluence() {
        return new InfluenceKeyframe(0, 20, 50, 50, 50, 50, 0, null);
    }


    public Spirit(InputHandler input) {
        super(input);

        isFloating = true;
        isSolid = false;

        viewDist = 4;
        baseReach = 1;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.03;

        maxHealth = 10;
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";
    }
}
