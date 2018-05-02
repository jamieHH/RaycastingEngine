package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

import java.util.List;

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
        List<Mob> mobs = getMobsInRadius(detonationRadius);

        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).hurt(this, detonationMagnitude);
        }

        for (int i = 0; i < 6; i++) {
            level.addEntity(new PoofParticle(posX, posZ));
        }
    }
}
