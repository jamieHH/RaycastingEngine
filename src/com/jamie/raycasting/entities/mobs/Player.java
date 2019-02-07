package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.items.MiscItem;
import com.jamie.raycasting.items.consumables.*;
import com.jamie.raycasting.items.weapons.*;

public class Player extends Mob
{
    protected Sprite setSprite() {
        return null;
    }

    protected Sprite setActionSprite() {
        return null;
    }

    protected Sprite setHealSprite() {
        return null;
    }

    protected Sprite setHurtSprite() {
        return null;
    }

    protected Sprite setDeathSprite() {
        return null;
    }


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
        health = maxHealth;

        faction = "human";
        enemyFaction = "beast";

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
        addItem(new KnifeWeapon());
        addItem(new PoisonPotion());
        addItem(new MiscItem("Silver Key", Texture.keyIcon));
        addItem(new MiscItem("Planks", Texture.planksIcon));
    }
}
