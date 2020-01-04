package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.EmberParticle;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.world.levels.Level;

import java.util.List;

public class ExplosionEffect extends EnvironmentalEffect
{
    protected Sprite getDefaultSprite() {
        return null;
    }

    protected Particle getParticle() {
        return new EmberParticle(32);
    }

    public ExplosionEffect(Level level, double radius, int magnitude) {
        super(level, radius, magnitude);
    }

    public void activate() {
        emitSound(Sound.die);
        List<Mob> mobs = getMobsInRadius(radius);

        for (int i = 0; i < mobs.size(); i++) {
            if (!mobs.get(i).isDead) {
                mobs.get(i).hurt(this, magnitude, "fire");
            }
        }

        level.addEntity(getParticle(), posX, posZ);
    }
}
