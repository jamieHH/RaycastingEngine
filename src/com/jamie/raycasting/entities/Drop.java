package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Item;

public class Drop extends Entity
{
    public Item item;

    protected Sprite getDefaultSprite() {
        return new DropSprite(Texture.bag);
    }


    public Drop(Item item) {
        if (item.dropSprite != null) {
            setIdleSprite(item.dropSprite);
        } else {
            setIdleSprite(getDefaultSprite());
        }

        radius = 1;
        isSolid = false;

        this.item = item;
    }
}
