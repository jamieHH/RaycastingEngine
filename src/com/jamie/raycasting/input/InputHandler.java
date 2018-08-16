package com.jamie.raycasting.input;

import java.awt.event.KeyEvent;
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

    public InputHandler() {
        int[] hot1 = {KeyEvent.VK_1};
        int[] hot2 = {KeyEvent.VK_2};
        int[] hot3 = {KeyEvent.VK_3};

        inputGroups.put("hot1", hot1);
        inputGroups.put("hot2", hot2);
        inputGroups.put("hot3", hot3);
    }


	public void tick() {}

    public void setInputState(String inputGroup, boolean state) {
        // setup for non key events
    }

	public void resetInfluence() {
        forwardInf = 50;
        backInf = 50;
        leftInf = 50;
        rightInf = 50;
        rotLeftInf = 50;
        rotRightInf = 50;
    }
}
