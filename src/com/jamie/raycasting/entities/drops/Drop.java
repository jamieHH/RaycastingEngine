package com.jamie.raycasting.entities.drops;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.items.Item;

public abstract class Drop extends Entity
{
    public Item item;

    public Drop(int x, int z) {
        setPosition(x, z);
        posY = 0.5;
        radius = 1;
        solid = false;
    }

    public void tick() {
        super.tick();

        if (posY > 0) {
            posY -= 0.05;
        } else {
            posY = 0;
        }
    }
}
