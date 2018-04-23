package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;

public abstract class MobEffect
{
    // TODO: build this base class to make effects that interact with a mob
    // such as, igniteEffect, modifyHealthEffect, modifySpeedEffect, desolidifyEffect, stunEffect, etc
    // effects will have a duration of active effect and be removed afterwards
    // the effect may be interval based if it increases health over time
    // mobs will have an arraylist of effects active on the mob

    protected Mob mob;
    public boolean removed = false;

    private int interval = 60;
    private int duration = 240;
    public int magnitude;
    public String name = "Effect";


    public MobEffect(int duration, int magnitude) {
        this.duration = duration;
        this.magnitude = magnitude;
    }

    public void tick() {
        if (duration > 0) {
            if (duration % interval == 0) {
                effect();
            }

            duration--;
        } else {
            deactivate();
            remove();
        }
    }

    public void effect() {
        // reset any mob variables changed to the original state
    }

    public void deactivate() {
        // reset any mob variables changed to the original state
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }

    private void remove() {
        removed = true;
    }
}
