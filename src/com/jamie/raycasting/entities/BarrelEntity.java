package com.jamie.raycasting.entities;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Bat;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.DarkWoodParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.ArtificialInputHandler;
import com.jamie.raycasting.items.consumables.HealthPotion;
import com.jamie.raycasting.items.consumables.SpeedPotion;
import com.jamie.raycasting.items.consumables.StrengthPotion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BarrelEntity extends Entity
{
    private Entity drop;
    private boolean used = false;

    public BarrelEntity(Entity drop) {
        setupSprites();
        this.isSolid = true;
        this.radius = 0.25;
        this.drop = drop;
    }

    public BarrelEntity(Mob drop) {
        setupSprites();
        this.isSolid = true;
        this.radius = 0.25;
        this.drop = drop;
    }

    public static Entity getLootItem() {
        Random random = new Random();
        ArrayList<Entity> list = new ArrayList<Entity>(
                Arrays.asList(
                        null,
                        null,
                        null,
                        null,
                        new Drop(new HealthPotion()),
                        new Drop(new SpeedPotion()),
                        new Drop(new StrengthPotion()),
                        new Bat(new ArtificialInputHandler())
                )
        );

        return list.get(random.nextInt(list.size()));
    }

    private void setupSprites() {
        Bitmap[] ts0 = {
                Texture.barrel0,
        };
        setIdleSprite(new Sprite(ts0));

        Bitmap[] ts1 = {
                Texture.barrel1,
        };
        setSpriteSet("broken", new Sprite(ts1));
    }

    public boolean use(Mob source) {
        if (!used) {
            used = true;
            isSolid = false;
            emitSound(Sound.smash);
            switchSpriteSet("broken");
            level.addEntity(new DarkWoodParticle(6), posX, posZ);
            if (drop != null) {
                level.addEntity(drop, posX, posZ);
            }
            return true;
        }

        return false;
    }

    protected Sprite getSprite() {
        return null; // gets overwritten in setupSprites
    }
}
