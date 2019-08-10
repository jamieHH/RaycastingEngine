package com.jamie.raycasting.world.blocks;

public abstract class TriggerableBlock extends FunctionBlock
{
    public TriggerableBlock() {
        isUsable = true;
    }

    public abstract void trigger();
}
