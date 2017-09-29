package com.jamie.raycasting.input;

import com.jamie.raycasting.entities.mobs.Mob;

public class InputHandler
{
	public boolean forward, back, left, right, rotLeft, rotRight, crouch, run, action;

	protected Mob mob;

	public int pauseTime = 0;
	public int disableTime = 0;

	public void setMob(Mob mob) {
        this.mob = mob;
    }

	public void tick() {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

        if (disableTime > 0) {
            disableTime--;
            disableInputs();
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
