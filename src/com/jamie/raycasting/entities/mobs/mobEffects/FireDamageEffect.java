package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.EmberParticle;
import com.jamie.jamapp.Texture;

public class FireDamageEffect extends MobEffect
{
    public FireDamageEffect(Mob mob, int duration, int magnitude) {
        super(mob, duration, magnitude);

        name = "Fire Damage";
        interval = 30;

        effectHudColour = 0xFF7000;
        effectHudIcon = Texture.fireIcon;
    }

    public void effect() {
        mob.hurt(mob, magnitude, "fire");

        EmberParticle e = new EmberParticle(8);
        mob.level.addEntity(e, mob.posX, mob.posZ);
    }
}
