package com.jamie.raycasting.items;

import com.jamie.jamapp.Render;

public class MiscItem extends Item
{
    public MiscItem(String name, Render icon) {
        super();

        this.name = name;
        type = "misc";
        canStrike = false;

        this.icon = icon;
    }
}
