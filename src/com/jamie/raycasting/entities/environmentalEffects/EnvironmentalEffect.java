package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.world.levels.Level;

public class EnvironmentalEffect extends Entity
{
    public String name = "Effect";

    protected boolean isInstant;
    protected int interval;

    protected int duration;
    protected int magnitude;

    public EnvironmentalEffect(Level level, int duration, double radius, int magnitude) {
        isSolid = false;
        this.level = level;
        this.radius = radius;

        this.magnitude = magnitude;
        this.duration = duration;
        isInstant = false;
    }

    public EnvironmentalEffect(Level level, double radius, int magnitude) {
        isSolid = false;
        this.level = level;
        this.radius = radius;

        this.magnitude = magnitude;
        this.duration = 1;
        isInstant = true;
    }

    public void tick() {
        super.tick();
        if (duration > 0) {
            if (isInstant) {
                activate();
                remove();
            } else {
                if (duration % interval == 0) { // TODO: check if instant (one off) or lingering effect
                    activate();
                }

                duration--;
            }
        } else {
            remove();
        }
    }

    public void activate() {

    }
}
