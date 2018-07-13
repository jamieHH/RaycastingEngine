package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.items.consumables.HealthPotion;

public class PrisonLevel extends Level
{
	public PrisonLevel() {
        name = "Prison";
	}

	protected void postCreate() {
	    Drop d = new Drop(new HealthPotion());
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
