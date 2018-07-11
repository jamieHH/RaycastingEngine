package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.items.Item;

import java.util.Map;

public abstract class Weapon extends Item
{
    public Weapon() {
        super();

        name = "Weapon";
        type = "weapon";
    }

    public Map<String, String> getInfo() {
        info = super.getInfo();
        info.put("damage", Integer.toString(damage + user.baseDamage));
        return info;
    }
}
