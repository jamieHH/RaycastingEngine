package com.jamie.raycasting.input;

import java.util.HashMap;
import java.util.Map;

public class InputHandler
{
    protected Map<String, int[]> inputGroups = new HashMap<String, int[]>();
    public boolean forward, back, left, right, rotLeft, rotRight, crouch, run, action, hot1, hot2, hot3;

    public int forwardInf = 50;
    public int backInf = 50;
    public int leftInf = 50;
    public int rightInf = 50;
    public int rotLeftInf = 50;
    public int rotRightInf = 50;


	public void tick() {}

    public void setInputState(String inputGroup, boolean state) {}

	public void resetInfluence() {
        forwardInf = 50;
        backInf = 50;
        leftInf = 50;
        rightInf = 50;
        rotLeftInf = 50;
        rotRightInf = 50;
    }
}
