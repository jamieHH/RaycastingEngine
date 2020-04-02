package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Item;

public class Drop extends Entity
{
    public final Item item;

    public Drop(Item item) {
        if (item.dropSprite != null) {
            setIdleSprite(item.dropSprite);
        } else {
            setIdleSprite(new DropSprite(Texture.bag));
        }

        radius = 1;
        isSolid = false;

        this.item = item;
    }
}
