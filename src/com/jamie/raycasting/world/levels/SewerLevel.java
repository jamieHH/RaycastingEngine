package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.mobs.Mob;

public class SewerLevel extends Level
{
	public SewerLevel() {
	    name = "Sewer";
	}

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "prison", 1);
        if (id == 2) world.switchLevel(mob, "island", 3);
    }
}
