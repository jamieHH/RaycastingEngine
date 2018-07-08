package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.mobEffects.FireDamageEffect;
import com.jamie.raycasting.entities.particles.EmberParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.world.levels.Level;

import java.util.List;

public class FireEffect extends EnvironmentalEffect
{
    public FireEffect(Level level, int duration, double radius, int magnitude) {
        super(level, duration, radius, magnitude);

        isInstant = false;
        interval = 60;

        name = "Fire";

        Render[] ts = {
                Texture.fire0,
                Texture.fire1,
                Texture.fire2,
                Texture.fire3,
        };
        setIdleSprite(new Sprite(ts));
    }

    public void activate() {
        List<Mob> mobs = getMobsInRadius(radius);

        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).addMobEffect(new FireDamageEffect(mobs.get(i), 60, magnitude));
        }

        EmberParticle p = new EmberParticle();
        level.addEntity(p, posX, posZ);
    }
}
