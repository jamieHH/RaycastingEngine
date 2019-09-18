package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;

public abstract class MobEffect
{
    public String name = "Effect";
    public boolean removed = false;

    protected Mob mob;
    protected int interval = 60;
    public int maxDuration;
    public int duration;
    public int magnitude;

    public int effectHudColour = 0xFFFFFF;
    public Bitmap effectHudIcon = Texture.poisonIcon;


    public MobEffect(Mob mob, int duration, int magnitude) {
        this.mob = mob;
        this.maxDuration = duration;
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
