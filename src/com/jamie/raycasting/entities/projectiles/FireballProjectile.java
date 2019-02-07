package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.entities.environmentalEffects.ExplosionEffect;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FireballProjectile extends Projectile
{
    protected Sprite getSprite() {
        return new Sprite(new Render[] {
                Texture.fireball0,
                Texture.fireball1
        });
    }


    public FireballProjectile(double detonationRadius, int detonationMagnitude) {
        super(detonationRadius, detonationMagnitude);

        Render[] ts2 = {
                Texture.explode0,
                Texture.explode1,
                Texture.explode2,
                Texture.explode3,
        };
        setDetonationSprite(new Sprite(ts2));
    }

    public void detonate() {
        super.detonate();
        ExplosionEffect effect = new ExplosionEffect(level, detonationRadius, detonationMagnitude);
        level.addEntity(effect, posX, posZ);
    }
}
