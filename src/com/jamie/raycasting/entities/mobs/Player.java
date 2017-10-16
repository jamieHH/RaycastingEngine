package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.BloodParticle;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.items.weapons.SpearWeapon;

public class Player extends Mob
{
	public Player(InputHandler input) {
        super(input);

//        wallCollide = false;
//        entCollide = false;

	    baseReach = 32;
	    viewDist = 4096;

	    radius = 4;

        rotationSpeed = 0.03;
        walkSpeed = 0.4; // disabling walking
        runSpeed = 0.4;
        crouchSpeed = 0.15;

        camHeightMod = 2.0;
        crouchHeightMod = 4.0;

//        maxHealth = 10;
        maxHealth = 100;
        health = maxHealth;

        hurtParticle = new BloodParticle(0, 0);

        addItem(new SpearWeapon());
    }
}
