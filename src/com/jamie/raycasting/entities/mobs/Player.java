package com.jamie.raycasting.entities.mobs;

import com.jamie.jamapp.App;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.jamapp.InputHandler;
import com.jamie.raycasting.items.MiscItem;
import com.jamie.raycasting.items.consumables.*;
import com.jamie.raycasting.items.weapons.*;

public class Player extends Mob
{
    protected Sprite getIdleSprite() {
        return new Sprite(new Bitmap[] {
                Texture.marker
        });
    }

    protected Sprite getActionSprite() {
        return null;
    }

    protected Sprite getHealSprite() {
        return null;
    }

    protected Sprite getHurtSprite() {
        return null;
    }

    protected Sprite getDeathSprite() {
        return null;
    }

    protected AiKeyFrame getIdleInfluence() {
        return null;
    }

    protected AiKeyFrame getPursuitInfluence() {
        return null;
    }

    protected AiKeyFrame getAttackInfluence() {
        return null;
    }


    public Player(InputHandler input) {
        super(input);

        canPickup = true;
        canActivateBlocks = true;

        isFloating = false;
        isSolid = true;

        viewDist = 8;
        baseReach = 2;

	    radius = 0.25;
        rotationSpeed = 0.03;
        walkSpeed = 0.1;

        maxHealth = 10;
        health = maxHealth;

        faction = "human";
        enemyFaction = "";

        hurtSound = Sound.pain;
        useWait = 15;

        if (App.getIsInDev()) {
            addItem(new SpearWeapon());
            addItem(new StrengthPotion());
            addItem(new WeaknessPotion());
            addItem(new SpeedPotion());
            addItem(new SlowPotion());
            addItem(new WandWeapon());
            addItem(new SuperWandWeapon());
            addItem(new HealthPotion());
            addItem(new XbowWeapon());
            addItem(new AxeWeapon());
            addItem(new KnifeWeapon());
            addItem(new PoisonPotion());
            addItem(new MiscItem("Silver Key", Texture.keyIcon));
            addItem(new MiscItem("Planks", Texture.planksIcon));
        }
    }
}
