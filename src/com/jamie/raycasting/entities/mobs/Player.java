package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.items.MiscItem;
import com.jamie.raycasting.items.consumables.*;
import com.jamie.raycasting.items.weapons.*;

import java.util.ArrayList;
import java.util.List;

public class Player extends Mob
{
    protected Sprite getSprite() {
        return null;
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

    protected List<InfluenceKeyframe> getInfluenceKeyframes() {
        return new ArrayList<InfluenceKeyframe>();
    }

    protected InfluenceKeyframe getIdleInfluence() {
        return new InfluenceKeyframe(0, 20, 0, 0, 0, 0, 0);
    }


    public Player(InputHandler input) {
        super(input);

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
