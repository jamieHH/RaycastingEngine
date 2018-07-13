package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.MiscItem;

public class GraveyardLevel extends Level
{
	public GraveyardLevel() {
		name = "Graveyard";
		height = 8192;
	}

	protected void postCreate() {
		Drop d = new Drop(new MiscItem("Grey Key", Texture.keyIcon));
		addEntity(d, 15.5, 58.5);
	}

    public void switchLevel(Mob mob, int id) {
        if (id == 3) world.switchLevel(mob, "island", 1);
    }
}
