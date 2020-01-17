package com.jamie.raycasting.items;

import com.jamie.jamapp.Bitmap;

public class MiscItem extends Item
{
    public MiscItem(String name, Bitmap icon) {
        super();
        type = TYPE_MISC;
        canStrike = false;

        this.name = name;
        this.icon = icon;
    }
}
