package com.jamie.raycasting.entities;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Item;

public class ChestEntity extends Entity
{
    private Item drop;
    private int quantity = 1;
    private boolean used = false;

    public ChestEntity(Item drop, int quantity) {
        setupSprites();
        this.isSolid = true;
        this.radius = 0.25;
        this.quantity = quantity;
        this.drop = drop;
    }

    public ChestEntity(Item drop) {
        setupSprites();
        this.isSolid = true;
        this.radius = 0.25;
        this.drop = drop;
    }

    private void setupSprites() {
        Bitmap[] ts0 = {
                Texture.chest0,
        };
        setIdleSprite(new Sprite(ts0));

        Bitmap[] ts1 = {
                Texture.chest1,
        };
        setSpriteSet("open", new Sprite(ts1));
    }

    public boolean use(Mob source) {
        if (!used) {
            used = true;
            isSolid = false;
            emitSound(Sound.pickUp);
            switchSpriteSet("open");
            for (int i = 0; i < quantity; i++) {
                source.addItem(drop);
            }
            return true;
        }

        return false;
    }

    protected Sprite getSprite() {
        return null; // gets overwritten in setupSprites
    }
}
