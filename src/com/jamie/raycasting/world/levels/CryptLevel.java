package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.mobs.Mob;

public class CryptLevel extends Level
{
	public CryptLevel() {
	    name = "Crypt";
	}

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "island", 3);
    }
}
