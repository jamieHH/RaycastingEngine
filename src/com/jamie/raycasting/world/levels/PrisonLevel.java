package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.drops.HealthPotionDrop;

public class PrisonLevel extends Level
{
	public PrisonLevel() {
        name = "Prison";
	}

	protected void postCreate() {
        addEntity(new HealthPotionDrop(16.5, 20.5));
    }

    public void switchLevel(int id) {
        if (id == 1) world.switchLevel("sewer", 1);
        if (id == 2) world.switchLevel("dungeon", 1);
    }

    public void triggerBlock(int id) {
        if (id == 1) super.triggerBlock(2);
    }
}
