package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.mobs.Mob;

public class CastleHallLevel extends Level
{
	public CastleHallLevel() {
	    name = "CastleHall";
	}

    protected void postCreate() {

    }

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "island", 2);
        if (id == 2) world.switchLevel(mob, "barracks", 1);
    }
}
