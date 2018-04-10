package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.drops.AxeDrop;

public class PrisonLevel extends Level
{
	public PrisonLevel() {
        name = "Prison";

        addEntity(new AxeDrop(16.5, 20.5));
	}

    public void switchLevel(int id) {
        if (id == 1) world.switchLevel("sewer", 1);
        if (id == 2) world.switchLevel("dungeon", 1);
    }

    public void activateBlock(int id) {
        if (id == 1) super.activateBlock(2);
    }
}