package com.jamie.raycasting.entities;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Item;
import com.jamie.raycasting.items.consumables.HealthPotion;
import com.jamie.raycasting.items.consumables.SpeedPotion;
import com.jamie.raycasting.items.consumables.StrengthPotion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ChestEntity extends Entity
{
    private Item drop;
    private boolean used = false;

    public ChestEntity(Item drop) {
        setupSprites();
        this.isSolid = true;
        this.radius = 0.25;
        this.drop = drop;
    }

    public static Item getLootItem() {
        Random random = new Random();
        ArrayList<Item> list = new ArrayList<Item>(
                Arrays.asList(
                        new HealthPotion(),
                        new SpeedPotion(),
                        new StrengthPotion(),
                        new StrengthPotion()
                )
        );

        return list.get(random.nextInt(list.size()));
    }

    private void setupSprites() {
        setIdleSprite(getDefaultSprite());
        setSpriteSet("open", new Sprite(Texture.chest1));
    }

    public boolean use(Mob source) {
        if (!used) {
            used = true;
            isSolid = false;
            emitSound(Sound.pickUp);
            switchSpriteSet("open");
            source.addItem(drop);
            return true;
        }

        return false;
    }

    protected Sprite getDefaultSprite() {
        return new Sprite(Texture.chest0);
    }
}
