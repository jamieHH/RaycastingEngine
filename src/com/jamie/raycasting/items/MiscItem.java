package com.jamie.raycasting.items;

import com.jamie.raycasting.graphics.Render;

public class MiscItem extends Item
{
    public MiscItem(String name, Render icon) {
        super();

        this.name = name;
        this.icon = icon;
        type = "misc";
    }
}
