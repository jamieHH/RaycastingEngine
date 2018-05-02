package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.world.levels.Level;

import java.util.List;

public class ExplosionEffect extends EnvironmentalEffect
{
    public ExplosionEffect(Level level, int duration, double radius, int magnitude) {
        super(level, duration, radius, magnitude);

        name = "Explosion";
    }

    public void tick() {
        super.tick();

        activate();
    }

    public void activate() {
        List<Mob> mobs = getMobsInRadius(radius);

        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).hurt(this, magnitude);
        }

        for (int i = 0; i < 6; i++) {
            level.addEntity(new PoofParticle(posX, posZ));
        }

        remove();
    }
}
