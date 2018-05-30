package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.items.consumables.*;
import com.jamie.raycasting.items.weapons.AxeWeapon;
import com.jamie.raycasting.items.weapons.SpearWeapon;
import com.jamie.raycasting.items.weapons.WandWeapon;
import com.jamie.raycasting.items.weapons.XbowWeapon;

public class Player extends Mob
{
	public Player(InputHandler input) {
        super(input);

        isFloating = false;
        isSolid = true;

	    baseReach = 2;
	    viewDist = 32;

	    radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.1;
        runSpeed = 0.1;
        crouchSpeed = 0.1;

        maxHealth = 10;

        addItem(new SpearWeapon());
        addItem(new StrengthPotion());
        addItem(new WeaknessPotion());
        addItem(new SpeedPotion());
        addItem(new SlowPotion());
        addItem(new WandWeapon());
        addItem(new HealthPotion());
        addItem(new XbowWeapon());
        addItem(new AxeWeapon());
        addItem(new PoisonPotion());

        addFaction("human");
    }
}
