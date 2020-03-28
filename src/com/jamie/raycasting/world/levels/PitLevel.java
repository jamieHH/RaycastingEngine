package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Texture;

public class PitLevel extends Level
{
	public PitLevel() {
        name = "Pit";
	}

    protected Bitmap getFloorTexture() {
        return Texture.dirt;
    }

    protected Bitmap getCeilingTexture() {
        return Texture.dirt;
    }

    protected Bitmap getWallTexture() {
        return Texture.wall2;
    }

	protected void postCreate() {

    }

    public void switchLevel(Entity entity, int id) {
        if (id == 1) world.switchLevel(entity, "island", 4);
    }

    public void triggerBlock(int id) {
        if (id == 2) super.triggerBlock(15);
    }
}
