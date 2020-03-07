package com.jamie.raycasting.world;

public class LogicTimer extends Logic
{
    private String output;
    private int triggerWait;
    private int triggerTicks;
    private boolean isActive = false;

    public LogicTimer(String reference, String output, int ticks) {
        this.reference = reference;
        this.output = output;
        this.triggerWait = ticks;
    }

    public void tick() {
        if (isActive) {
            if (triggerTicks > 0) {
                triggerTicks--;
            } else {
                triggerTicks = triggerWait;
                level.triggerBlock(output);
            }
        }
    }

    public void trigger() {
        setState(!getState());
    }

    public boolean getState() {
        return isActive;
    }

    protected void setState(boolean state) {
        isActive = state;
    }
}
