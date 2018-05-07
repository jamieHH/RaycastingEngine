package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;

public abstract class MobEffect
{
    public String name = "Effect";
    public boolean removed = false;

    protected Mob mob;
    private int interval = 60;
    private int duration = 240;
    public int magnitude;


    public MobEffect(Mob mob, int duration, int magnitude) {
        this.mob = mob;
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
