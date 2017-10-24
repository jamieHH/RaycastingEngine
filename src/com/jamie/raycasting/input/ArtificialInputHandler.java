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
            forward = (random.nextInt(100) < forwardInf); //false;
            back = (random.nextInt(100) < backInf); //false;
            left = (random.nextInt(100) < leftInf); //false;
            right = (random.nextInt(100) < rightInf); //false;
            rotLeft = (random.nextInt(100) < rotLeftInf); //false;
            rotRight = (random.nextInt(100) < rotRightInf); //false;
        }

        changeTicks = changeWait;
	}
}
