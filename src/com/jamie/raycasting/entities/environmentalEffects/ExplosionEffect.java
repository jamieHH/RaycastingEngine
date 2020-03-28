package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.ExplosiveBarrelEntity;
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
        List<Entity> entities = getEntitiesInRadius(radius);

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entities.get(i) instanceof Mob) {
                if (!((Mob) entity).isDead) {
                    ((Mob) entity).hurt(this, magnitude, "fire");
                }
            } else if (entity instanceof ExplosiveBarrelEntity) {
                ((ExplosiveBarrelEntity) entity).detonate();
            }
        }

        level.addEntity(getParticle(), posX, posZ);
    }
}
