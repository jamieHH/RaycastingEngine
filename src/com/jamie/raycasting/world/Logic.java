package com.jamie.raycasting.world;

import com.jamie.raycasting.world.levels.Level;

public abstract class Logic
{
    public String reference;
    public Level level;

    public abstract void tick();

    public abstract void trigger();
}
