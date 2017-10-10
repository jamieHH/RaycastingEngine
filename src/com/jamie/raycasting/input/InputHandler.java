package com.jamie.raycasting.input;

import com.jamie.raycasting.entities.mobs.Mob;

public class InputHandler
{
    protected Mob mob;

	public boolean forward, back, left, right, rotLeft, rotRight, crouch, run, action;

    public int forwardInf = 50;
    public int backInf = 50;
    public int leftInf = 50;
    public int rightInf = 50;
    public int rotLeftInf = 50;
    public int rotRightInf = 50;

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

	public void resetInfluence() {
        forwardInf = 50;
        backInf = 50;
        leftInf = 50;
        rightInf = 50;
        rotLeftInf = 50;
        rotRightInf = 50;
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
