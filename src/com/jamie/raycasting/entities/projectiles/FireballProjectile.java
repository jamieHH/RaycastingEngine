package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.entities.environmentalEffects.ExplosionEffect;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FireballProjectile extends Projectile
{
    protected Sprite getSprite() {
        return new Sprite(new Bitmap[] {
                Texture.fireball0,
                Texture.fireball1
        });
    }


    public FireballProjectile(double detonationRadius, int detonationMagnitude) {
        super(detonationRadius, detonationMagnitude);

        Bitmap[] ts2 = {
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
