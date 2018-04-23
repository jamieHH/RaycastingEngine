package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;

public class ModSpeedEffect extends MobEffect
{
    public ModSpeedEffect(Mob mob, int duration, int magnitude) {
        super(mob, duration, magnitude);

        name = "Mod Speed";

        mob.modSpeed(magnitude / 100);
    }

    public void effect() {

    }

    public void deactivate() {
        mob.modSpeed(-magnitude / 100);
    }
}
