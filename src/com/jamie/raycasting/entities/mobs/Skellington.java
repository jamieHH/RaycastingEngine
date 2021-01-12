package com.jamie.raycasting.entities.mobs;

import com.jamie.jamapp.Bitmap;
import com.jamie.jamapp.InputHandler;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class Skellington extends Mob
{
    protected Sprite getIdleSprite() {
        return new Sprite(new Bitmap[] {
                Texture.skellington1,
                Texture.skellington1,
                Texture.skellington0,
                Texture.skellington0,
                Texture.skellington2,
                Texture.skellington2,
                Texture.skellington0,
                Texture.skellington0,
        });
    }

    protected Sprite getActionSprite() {
        return new Sprite(new Bitmap[] {
                Texture.skellingtonAtt0,
                Texture.skellingtonAtt0,
                Texture.skellingtonAtt0,
        });
    }

    protected Sprite getHealSprite() {
        return new Sprite(new Bitmap[] {
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0,
                Texture.spiritHeal0
        });
    }

    protected Sprite getHurtSprite() {
        return new Sprite(new Bitmap[] {
                Texture.spiritHurt0,
                Texture.spiritHurt0,
                Texture.spiritHurt0
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

    protected Particle getHurtParticle() {
        return new PoofParticle(4);
    }

    protected AiKeyFrame getIdleInfluence() {
        return new AiKeyFrame(30, 50, 25, 25, 25, 50, 50, 0, null);
    }
    protected AiKeyFrame getPursuitInfluence() {
        return new AiKeyFrame(30, 100, 0, 50, 50, 0, 0, 0, null);
    }
    protected AiKeyFrame getAttackInfluence() {
        return new AiKeyFrame(30, 0, 0, 0, 0, 0, 0, 100, null);
    }


    public Skellington(InputHandler input) {
        super(input);

        isFloating = false;
        isSolid = true;

        viewDist = 8;
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
