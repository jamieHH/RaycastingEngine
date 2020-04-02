package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.mobEffects.FireDamageEffect;
import com.jamie.raycasting.entities.particles.EmberParticle;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.world.levels.Level;

import java.util.List;

public class FireEffect extends EnvironmentalEffect
{
    protected Particle getParticle() {
        return new EmberParticle(2);
    }


    public FireEffect(Level level, int duration, double radius, int magnitude) {
        super(level, duration, radius, magnitude);

        isInstant = false;
        interval = 60;
    }

    public void activate() {
        List<Entity> entities = getEntitiesInRadius(radius);

        for (int i = 0; i < entities.size(); i++) {
            if (entities instanceof Mob) {
                if (!((Mob) entities.get(i)).isDead) {
                    ((Mob) entities.get(i)).addMobEffect(new FireDamageEffect((Mob) entities.get(i), 60, magnitude));
                }
            }
        }

        level.addEntity(getParticle(), posX, posZ);
    }
}
