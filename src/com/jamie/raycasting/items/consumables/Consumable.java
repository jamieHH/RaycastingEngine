package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.items.Item;

public class Consumable extends Item
{
    public Consumable() {
        super();

        canStrike = false;
        name = "Consumable";
    }

    public void use() {
        super.use();

        remove();
    }
}
