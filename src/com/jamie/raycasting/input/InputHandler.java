package com.jamie.raycasting.input;

import java.util.ArrayList;

public class InputHandler {
	
	public boolean forward, back, left, right, rotLeft, rotRight, crouch, run, action;

	public int pauseTime = 0;
	public int disableTime = 0;

	public void tick() {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

        if (disableTime > 0) {
            disableTime--;
            disableInputs();
            return;
        }
	}

    protected void disableInputs() {
        forward = false;
        back  = false;
        left = false;
        right = false;
        rotLeft = false;
        rotRight = false;
        crouch = false;
        run = false;
        action = false;
    }
}
