package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.levels.blocks.Block;
import com.jamie.raycasting.levels.blocks.DoorBlock;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Mob {

	public Player(InputHandler input) {
	    this.input = input;

//        wallCollide = false;
//        entCollide = false;

	    useDist = 24;
	    viewDist = 4096;

	    radius = 4;

        rotationSpeed = 0.03;
        walkSpeed = 0.3;
        runSpeed = 0.4;
        crouchSpeed = 0.15;

        camHeightMod = 2.0;
        crouchHeightMod = 4.0;

        maxHealth = 10;
//        maxHealth = 100;
        health = maxHealth;
    }

	public void tick() {
        super.tick();

        if (isDead || isDieing) {
            camY = -6.0;
            return;
        }

        if (input.action) {
            if (useTime <= 0) {
                useTime = 30;

//            ((UserInputHandler) input).key[KeyEvent.VK_SPACE] = false;
//            ((UserInputHandler) input).key[KeyEvent.VK_ENTER] = false;

                activate();
            }
        }

        doMovements();
	}
}
