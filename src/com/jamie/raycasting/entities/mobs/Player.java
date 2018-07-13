package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.items.MiscItem;
import com.jamie.raycasting.items.consumables.*;
import com.jamie.raycasting.items.weapons.*;

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

        hurtSound = Sound.pain;

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
        addItem(new PlanksWeapon());
        addItem(new MiscItem("Silver Key"));

        addFaction("human");
    }
}
