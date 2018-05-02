package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.entities.environmentalEffects.ExplosionEffect;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FireballProjectile extends Projectile
{
    public FireballProjectile() {
        Render[] ts1 = {
                Texture.fireball0,
                Texture.fireball1
        };

        setIdleSprite(new Sprite(ts1));
    }

    public void detonate() {
        ExplosionEffect explosionEffect = new ExplosionEffect(level, 0, 2, 1);
        explosionEffect.setPosition(posX, posZ);
        level.addEntity(explosionEffect);
    }
}
