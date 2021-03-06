package com.jamie.raycasting.entities.mobs;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.jamapp.InputHandler;
import com.jamie.raycasting.input.ArtificialInputHandler;
import com.jamie.raycasting.items.specials.FireballSpell;
import com.jamie.raycasting.items.specials.HealingSpell;

public class Imp extends Mob
{
    protected Sprite getIdleSprite() {
        return new Sprite(new Bitmap[] {
                Texture.imp0,
                Texture.imp1,
                Texture.imp2,
                Texture.imp1,
        });
    }

    protected Sprite getActionSprite() {
        return new Sprite(new Bitmap[] {
                Texture.impAtt0,
                Texture.impAtt1,
                Texture.impAtt2,
                Texture.impAtt2,
                Texture.impAtt2,
                Texture.impAtt2,
        });
    }

    protected Sprite getHealSprite() {
        return new Sprite(new Bitmap[] {
                Texture.impHeal0,
                Texture.impHeal1,
                Texture.impHeal2,
                Texture.impHeal1,
                Texture.impHeal0,
                Texture.impHeal1,
        });
    }

    protected Sprite getHurtSprite() {
        return new Sprite(new Bitmap[] {
                Texture.impHurt0,
                Texture.impHurt1,
                Texture.impHurt2,
        });
    }

    protected Sprite getDeathSprite() {
        return new Sprite(new Bitmap[] {
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
                Texture.splat1,
        });
    }

    protected AiKeyFrame getIdleInfluence() {
        return new AiKeyFrame(20, 50, 50, 50, 50, 50, 50, 100, "Healing Spell");
    }
    protected AiKeyFrame getPursuitInfluence() {
        return new AiKeyFrame(20, 10, 100, 50, 50, 0, 0, 100, "Fireball Spell");
    }
    protected AiKeyFrame getAttackInfluence() {
        return new AiKeyFrame(10, 100, 50, 50, 50, 0, 0, 100, null);
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

    public Imp(Drop drop) {
        this(new ArtificialInputHandler());
        setDrop(drop);
    }
}
