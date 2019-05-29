package com.jamie.raycasting.input;

import java.util.HashMap;
import java.util.Map;

public abstract class InputHandler
{
    protected Map<String, int[]> inputGroups = new HashMap<String, int[]>();
    public boolean forward, back, left, right, rotLeft, rotRight, action, hot1, hot2, hot3;


	public void tick() {}

    public void setInputState(String inputGroup, boolean state) {}
}
