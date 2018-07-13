package com.jamie.raycasting.items;

import com.jamie.raycasting.graphics.Texture;

public class MiscItem extends Item
{
    public MiscItem(String name) {
        super();

        this.name = name;
        type = "key";

        icon = Texture.keyIcon;
    }
}
