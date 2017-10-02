package com.jamie.raycasting.input;

import java.util.Random;

public class ArtificialInputHandler extends InputHandler
{
    private boolean allowMovement = true;

	private Random random = new Random();
	private int changeTimer = 20;

	public void tick() {
        changeTimer--;
        if (changeTimer > 0) {
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

        changeTimer = 20;
	}
}
