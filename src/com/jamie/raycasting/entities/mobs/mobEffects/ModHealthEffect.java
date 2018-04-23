package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;

public class ModHealthEffect extends MobEffect
{
    public ModHealthEffect(Mob mob, int duration, int magnitude) {
        super(mob, duration, magnitude);

        name = "Mod Health";
    }

    public void effect() {
        mob.modHealth(magnitude);
    }

    public void deactivate() {
        // reset any mob variables changed to the original state
    }
}
