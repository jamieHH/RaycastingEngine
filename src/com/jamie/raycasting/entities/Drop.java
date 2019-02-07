package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Item;

public class Drop extends Entity
{
    public Item item;

    protected Sprite getSprite() {
        return new Sprite(Texture.blood0); // TODO: set sprite to items icon
    }


    public Drop(Item item) {
        posY = 0.5;
        radius = 1;
        isSolid = false;

        this.item = item;
    }

    public void tick() {
        super.tick();

        if (posY > 0) {
            posY -= 0.05;
        } else {
            posY = 0;
        }
    }
}
