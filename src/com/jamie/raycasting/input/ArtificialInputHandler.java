package com.jamie.raycasting.input;

import java.util.Random;

public class ArtificialInputHandler extends InputHandler
{
    private boolean allowMovement = true;

	private final Random random = new Random();
	private int changeTicks = 0;
	private int changeWait = 20;

	public void tick() {
        changeTicks--;
        if (changeTicks > 0) {
            return;
        }

        if (allowMovement) {
            forward = (random.nextInt(100) < forwardInf);
            back = (random.nextInt(100) < backInf);
            left = (random.nextInt(100) < leftInf);
            right = (random.nextInt(100) < rightInf);
            rotLeft = (random.nextInt(100) < rotLeftInf);
            rotRight = (random.nextInt(100) < rotRightInf);
        } else {
            forward = false;
            back = false;
            left = false;
            right = false;
            rotLeft = false;
            rotRight = false;
        }

        changeTicks = changeWait;
	}
}
