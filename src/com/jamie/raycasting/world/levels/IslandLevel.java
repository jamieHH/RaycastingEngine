package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.world.blocks.TeleporterBlock;

public class IslandLevel extends Level
{
	public IslandLevel() {
		name = "Island";
		height = 8192;
	}

	protected void postCreate() {
		TeleporterBlock teleporter0 = new TeleporterBlock();
		teleporter0.floorTex = Texture.grass;

		setBlock(29, 29, teleporter0);
	}

    public void switchLevel(int id) {
        if (id == 1) world.switchLevel("castleHall", 1);
        if (id == 2) world.switchLevel("sewer", 2);
    }
}
