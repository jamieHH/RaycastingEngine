package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.AreaAlertEntity;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.MiscItem;

public class GraveyardLevel extends Level
{
	public GraveyardLevel() {
		name = "Graveyard";
		height = 64;
        fogColor = 0x000010;
		isOutside = true;
	}

	protected Bitmap getFloorTexture() {
		return Texture.grass;
	}

	protected Bitmap getCeilingTexture() {
		return null;
	}

	protected void postCreate() {
		Drop d = new Drop(new MiscItem("Grey Key", Texture.keyIcon));
		addEntity(d, 14.5, 51.5);

		Drop d1 = new Drop(new MiscItem("Planks", Texture.planksIcon));
		addEntity(d1, 14.5, 44.5);

		AreaAlertEntity a0 = new AreaAlertEntity("I can use these Planks to repair the bridge.");
		addEntity(a0, 14.5, 44.5);
	}

    public void switchLevel(Entity entity, int id) {
        if (id == 2) world.switchLevel(entity, "island", 1);
    }

	public void triggerBlock(int id) {
		if (id == 1) super.triggerBlock(2);
	}
}
