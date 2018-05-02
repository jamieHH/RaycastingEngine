package com.jamie.raycasting.entities.environmentalEffects;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.world.levels.Level;

public class EnvironmentalEffect extends Entity
{
    // TODO: build this base class for effects that interact with other entities in the world
    // ...such as fire, explosions, splashes, etc

    public String name = "Effect";

    protected Level level;
    protected int interval = 60;

    protected int duration = 240;
    protected int magnitude = 0;
    protected double radius = 1;

    public EnvironmentalEffect(Level level, int duration, double radius, int magnitude) {
        this.level = level;
        this.duration = duration;
        this.radius = radius;
        this.magnitude = magnitude;

        isSolid = false;
    }

    public void tick() {
        super.tick();
        if (duration > 0) {
            duration--;
        } else {
            remove();
        }
    }

    public void activate() {

    }
}
