package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.EmberParticle;
import com.jamie.raycasting.graphics.Texture;

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
        mob.level.addEntity(new EmberParticle(mob.posX, mob.posZ));
    }
}
