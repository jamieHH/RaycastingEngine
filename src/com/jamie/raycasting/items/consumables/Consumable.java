package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.items.Item;

public class Consumable extends Item
{
    public Consumable() {
        super();

        name = "Consumable";
        type = "consumable";
        canStrike = false;
    }

    public void use() {
        super.use();

        remove();
    }
}
