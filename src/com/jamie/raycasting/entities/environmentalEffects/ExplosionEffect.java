package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.ExplosiveBarrelEntity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.EmberParticle;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.world.blocks.Block;
import com.jamie.raycasting.world.blocks.CrackedWallBlock;
import com.jamie.raycasting.world.levels.Level;

import java.util.List;

public class ExplosionEffect extends EnvironmentalEffect
{
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

        for (int x = (int) posX - 1; x <= (int) posX + 1; x++) {
            for (int z = (int) posZ - 1; z <= (int) posZ + 1; z++) {
                Block b = level.getBlock(x, z);
                if (b instanceof CrackedWallBlock) {
                    ((CrackedWallBlock) b).trigger();
                }
            }
        }

        level.addEntity(getParticle(), posX, posZ);
    }
}
