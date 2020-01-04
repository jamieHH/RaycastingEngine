package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.entities.particles.StoneParticle;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.jamapp.InputHandler;

public class Guardian extends Mob
{
    protected Sprite getDefaultSprite() {
        return new Sprite(new Bitmap[] {
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
        return new Sprite(new Bitmap[] {
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1
        });
    }

    protected Particle getHurtParticle() {
        return new StoneParticle(4);
    }

    protected InfluenceKeyframe getIdleInfluence() {
        return new InfluenceKeyframe(20, 0, 0, 0, 0, 0, 0, 0, null);
    }
    protected InfluenceKeyframe getPursuitInfluence() {
        return new InfluenceKeyframe(20, 100, 0, 0, 0, 0, 0, 0, null);
    }
    protected InfluenceKeyframe getAttackInfluence() {
        return new InfluenceKeyframe(10, 100, 0, 0, 0, 0, 0, 100, null);
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
