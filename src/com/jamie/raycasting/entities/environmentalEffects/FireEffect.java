package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.mobEffects.FireDamageEffect;
import com.jamie.raycasting.entities.particles.EmberParticle;
import com.jamie.jamapp.Render;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.world.levels.Level;

import java.util.List;

public class FireEffect extends EnvironmentalEffect
{
    protected Sprite getSprite() {
        return new Sprite(new Render[] {
                Texture.fire0,
                Texture.fire1,
                Texture.fire2,
                Texture.fire3,
        });
    }

    protected Particle getParticle() {
        return new EmberParticle(2);
    }


    public FireEffect(Level level, int duration, double radius, int magnitude) {
        super(level, duration, radius, magnitude);

        isInstant = false;
        interval = 60;
    }

    public void activate() {
        List<Mob> mobs = getMobsInRadius(radius);

        for (int i = 0; i < mobs.size(); i++) {
            if (!mobs.get(i).isDead) {
                mobs.get(i).addMobEffect(new FireDamageEffect(mobs.get(i), 60, magnitude));
            }
        }

        level.addEntity(getParticle(), posX, posZ);
    }
}
