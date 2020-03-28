package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.items.Item;

public abstract class Weapon extends Item
{
    public Weapon() {
        super();
        type = TYPE_WEAPON;
        canStrike = true;
    }
}
