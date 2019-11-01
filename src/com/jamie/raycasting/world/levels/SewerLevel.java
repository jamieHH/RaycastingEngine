package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.mobs.Mob;

public class SewerLevel extends Level
{
	public SewerLevel() {
	    name = "Sewer";
	}

    protected void postCreate() {

    }

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "prison", 1);
        if (id == 2) world.switchLevel(mob, "island", 5);
    }
}
