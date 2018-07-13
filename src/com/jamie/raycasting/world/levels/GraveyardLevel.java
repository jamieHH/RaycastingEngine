package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.drops.MiscItemDrop;
import com.jamie.raycasting.entities.mobs.Mob;

public class GraveyardLevel extends Level
{
	public GraveyardLevel() {
		name = "Graveyard";
		height = 8192;
	}

	protected void postCreate() {
		MiscItemDrop d = new MiscItemDrop("Grey Key");
		addEntity(d, 15.5, 58.5);
	}

    public void switchLevel(Mob mob, int id) {
        if (id == 3) world.switchLevel(mob, "island", 1);
    }
}
