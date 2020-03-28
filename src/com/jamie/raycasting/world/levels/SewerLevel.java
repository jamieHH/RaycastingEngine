package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Entity;

public class SewerLevel extends Level
{
	public SewerLevel() {
	    name = "Sewer";
	}

    protected void postCreate() {

    }

    public void switchLevel(Entity entity, int id) {
        if (id == 1) world.switchLevel(entity, "prison", 1);
        if (id == 2) world.switchLevel(entity, "island", 5);
    }
}
