package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.items.Item;

import java.util.Map;

public abstract class Weapon extends Item
{
    public Weapon() {
        super();

        name = "Weapon";
        type = "weapon";
        canStrike = true;
    }
}
