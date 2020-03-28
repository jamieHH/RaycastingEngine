package com.jamie.raycasting.entities;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.environmentalEffects.ExplosionEffect;
import com.jamie.raycasting.entities.particles.DarkWoodParticle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

import java.util.Random;

public class ExplosiveBarrelEntity extends Entity
{
    private int explodeWait;
    private boolean doDetonate = false;
    private boolean isDetonated = false;


    public ExplosiveBarrelEntity() {
        setIdleSprite(new Sprite(Texture.xBarrel0));
        setSpriteSet("broken", new Sprite(Texture.barrel1));

        explodeWait = new Random().nextInt(60);
    }

    public void tick() {
        super.tick();

        if (!isDetonated) {
            if (doDetonate) {
                if (explodeWait > 0) {
                    level.addEntity(new PoofParticle(6), posX, posZ);
                    explodeWait--;
                } else {
                    isDetonated = true;
                    isSolid = false;
                    emitSound(Sound.smash);
                    switchSpriteSet("broken");
                    level.addEntity(new DarkWoodParticle(12), posX, posZ);
                    ExplosionEffect effect = new ExplosionEffect(level, 2, 2);
                    level.addEntity(effect, posX, posZ);
                }
            }
        }
    }

    public void detonate() {
        doDetonate = true;
    }
}
