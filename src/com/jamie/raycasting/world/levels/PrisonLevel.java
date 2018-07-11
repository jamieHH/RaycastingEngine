package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.drops.HealthPotionDrop;
import com.jamie.raycasting.entities.mobs.Mob;

public class PrisonLevel extends Level
{
	public PrisonLevel() {
        name = "Prison";
	}

	protected void postCreate() {
	    HealthPotionDrop d = new HealthPotionDrop();
        addEntity(d, 16.5, 20.5);
    }

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "sewer", 1);
        if (id == 2) world.switchLevel(mob, "dungeon", 1);
    }

    public void triggerBlock(int id) {
        if (id == 1) super.triggerBlock(2);
    }
}
