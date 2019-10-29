package com.jamie.raycasting.world.blocks;

public abstract class TriggerableBlock extends FunctionBlock
{
    public TriggerableBlock() {
        isUsable = true;
    }

    public abstract void trigger();

    public abstract boolean getState();

    public abstract void setState(boolean state);
}
