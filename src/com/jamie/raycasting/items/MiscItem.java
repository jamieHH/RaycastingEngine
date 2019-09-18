package com.jamie.raycasting.items;

import com.jamie.jamapp.Bitmap;

public class MiscItem extends Item
{
    public MiscItem(String name, Bitmap icon) {
        super();

        this.name = name;
        type = "misc";
        canStrike = false;

        this.icon = icon;
    }
}
