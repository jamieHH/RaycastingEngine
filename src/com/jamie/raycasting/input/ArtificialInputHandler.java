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
            forward = random.nextBoolean(); //false;
            back = random.nextBoolean(); //false;
            left = random.nextBoolean(); //false;
            right = random.nextBoolean(); //false;
            rotLeft = random.nextBoolean(); //false;
            rotRight = random.nextBoolean(); //false;
        }

        changeTimer = 20;
	}
}
