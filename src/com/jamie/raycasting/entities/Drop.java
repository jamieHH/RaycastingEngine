package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Item;

public class Drop extends Entity
{
    public Item item;

    protected Sprite getSprite() {
        return new Sprite(Texture.bag);
    }


    public Drop(Item item) {
        radius = 1;
        isSolid = false;

        this.item = item;
    }
}
