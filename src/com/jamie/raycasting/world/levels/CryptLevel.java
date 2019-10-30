package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.items.weapons.WandWeapon;

public class CryptLevel extends Level
{
	public CryptLevel() {
	    name = "Crypt";
	}

    protected void postCreate() {
        addEntity(new Drop(new WandWeapon()), 28.5, 23.5);
    }

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "island", 3);
    }
}
