package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.mobs.Mob;

public class BarracksLevel extends Level
{
	public BarracksLevel() {
		name = "Barracks";
	}

	public void switchLevel(Mob mob, int id) {
		if (id == 1) world.switchLevel(mob, "castleHall", 2);
	}

    public void triggerBlock(int id) {
        if (id == 1) super.triggerBlock(1);
    }
}
