package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.entities.environmentalEffects.ExplosionEffect;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FireballProjectile extends Projectile
{
    public FireballProjectile(double detonationRadius, int detonationMagnitude) {
        super(detonationRadius, detonationMagnitude);

        Bitmap[] ts0 = {
                Texture.fireball0,
                Texture.fireball1
        };
        setIdleSprite(new Sprite(ts0));

        Bitmap[] ts1 = {
                Texture.explode0,
                Texture.explode1,
                Texture.explode2,
                Texture.explode3,
        };
        setDetonationSprite(new Sprite(ts1));
    }

    public void detonate() {
        super.detonate();
        ExplosionEffect effect = new ExplosionEffect(level, detonationRadius, detonationMagnitude);
        level.addEntity(effect, posX, posZ);
    }
}
