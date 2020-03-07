package com.jamie.raycasting.world;

public class LogicRelay extends Logic
{
    private String[] outputs;
    private boolean isActive;

    public LogicRelay(String reference, String[] outputs) {
        this.reference = reference;
        this.outputs = outputs;
    }

    public void tick() {

    }

    public void trigger() {
        for (String output : outputs) {
            level.triggerBlock(output);
        }
    }

    public boolean getState() {
        return isActive;
    }

    protected void setState(boolean state) {
        isActive = state;
    }
}
