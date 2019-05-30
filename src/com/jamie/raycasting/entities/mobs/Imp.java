package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.items.specials.FireballSpell;
import com.jamie.raycasting.items.specials.HealingSpell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Imp extends Mob
{
    protected Sprite getSprite() {
        return new Sprite(new Render[] {
                Texture.imp0,
                Texture.imp1,
                Texture.imp2,
                Texture.imp1,
        });
    }

    protected Sprite getActionSprite() {
        return new Sprite(new Render[] {
                Texture.impAtt0,
                Texture.impAtt1,
                Texture.impAtt2,
                Texture.impAtt2,
                Texture.impAtt2,
                Texture.impAtt2,
        });
    }

    protected Sprite getHealSprite() {
        return new Sprite(new Render[] {
                Texture.impHeal0,
                Texture.impHeal1,
                Texture.impHeal2,
                Texture.impHeal1,
                Texture.impHeal0,
                Texture.impHeal1,
        });
    }

    protected Sprite getHurtSprite() {
        return new Sprite(new Render[] {
                Texture.impHurt0,
                Texture.impHurt1,
                Texture.impHurt2,
        });
    }

    protected Sprite getDeathSprite() {
        return new Sprite(new Render[] {
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
        });
    }

    protected List<InfluenceKeyframe> getInfluenceKeyframes() {
        return new ArrayList<InfluenceKeyframe>(Arrays.asList( // keyframe list must be in order
                new InfluenceKeyframe(8, 20, 75, 0, 50, 50, 50, "Fireball Spell"), // 8 blocks will approach
                new InfluenceKeyframe(3, 20, 0, 0, 50, 50, 50, "Fireball Spell"), // 3 block will maintain distance
                new InfluenceKeyframe(2, 10, 0, 100, 50, 50, 100, null), // 2 block will move back
                new InfluenceKeyframe(1, 10, 0, 100, 0, 0, 100, null) // 1 block will move back and attack
        ));
    }

    protected InfluenceKeyframe getIdleInfluence() {
        return new InfluenceKeyframe(0, 20, 50, 50, 50, 50, 100, "Healing Spell");
    }


    public Imp(InputHandler input) {
        super(input);

        isFloating = true;
        isSolid = true;

        viewDist = 8;
        baseReach = 1;

        radius = 0.25;

        rotationSpeed = 0.03;
        walkSpeed = 0.04;

        maxHealth = 10;
        health = maxHealth;

        faction = "beast";
        enemyFaction = "human";

        addItem(new HealingSpell());
        addItem(new FireballSpell());
    }
}
