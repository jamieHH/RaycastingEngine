package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.particles.BloodParticle;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.items.consumables.HealthPotion;
import com.jamie.raycasting.items.weapons.SpearWeapon;
import com.jamie.raycasting.items.weapons.WandWeapon;

public class Player extends Mob
{
	public Player(InputHandler input) {
        super(input);

        isSolid = true;

	    baseReach = 2;
	    viewDist = 32;

	    radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.04; // disabling walking
        runSpeed = 0.04;
        crouchSpeed = 0.01;

        maxHealth = 10;
//        maxHealth = 100;

        hurtParticle = new BloodParticle(0, 0);

        addItem(new SpearWeapon());
        addItem(new HealthPotion());
        addItem(new WandWeapon());

        addFaction("human");
    }
}
