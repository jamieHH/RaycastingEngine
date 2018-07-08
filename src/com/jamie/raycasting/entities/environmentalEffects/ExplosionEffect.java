package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.EmberParticle;
import com.jamie.raycasting.world.levels.Level;

import java.util.List;

public class ExplosionEffect extends EnvironmentalEffect
{
    public ExplosionEffect(Level level, double radius, int magnitude) {
        super(level, radius, magnitude);

        name = "Explosion";
    }

    public void activate() {
        List<Mob> mobs = getMobsInRadius(radius);

        for (int i = 0; i < mobs.size(); i++) {
            if (!mobs.get(i).isDead) {
                mobs.get(i).hurt(this, magnitude, "fire");
            }
        }

        EmberParticle p = new EmberParticle();
        level.addEntity(p, posX, posZ);
    }
}
