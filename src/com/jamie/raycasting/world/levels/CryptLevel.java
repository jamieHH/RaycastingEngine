package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.items.weapons.XbowWeapon;

public class CryptLevel extends Level
{
	public CryptLevel() {
	    name = "Crypt";
	}

    protected void postCreate() {
        addEntity(new Drop(new XbowWeapon()), 28.5, 23.5);
    }

    public void switchLevel(Entity entity, int id) {
        if (id == 1) world.switchLevel(entity, "island", 3);
    }
}
