package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.items.Item;

import java.util.Map;

public abstract class Weapon extends Item
{
    public Weapon() {
        super();
        type = TYPE_WEAPON;
        canStrike = true;
    }
}
