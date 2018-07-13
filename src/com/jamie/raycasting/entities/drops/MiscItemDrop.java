package com.jamie.raycasting.entities.drops;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.MiscItem;

public class MiscItemDrop extends Drop
{
    public MiscItemDrop(String name, Render icon) {
        super();
        item = new MiscItem(name, icon);
        setIdleSprite(new Sprite(Texture.blood0));
    }
}
