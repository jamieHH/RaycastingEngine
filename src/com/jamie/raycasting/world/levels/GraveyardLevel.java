package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.drops.KeyDrop;
import com.jamie.raycasting.entities.mobs.Mob;

public class GraveyardLevel extends Level
{
	public GraveyardLevel() {
		name = "Graveyard";
		height = 8192;
	}

	protected void postCreate() {
		KeyDrop d = new KeyDrop("Grey Key", "grey key");
		addEntity(d, 15.5, 58.5);
	}

    public void switchLevel(Mob mob, int id) {
        if (id == 3) world.switchLevel(mob, "island", 1);
    }
}
