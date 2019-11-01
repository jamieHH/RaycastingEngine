package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class IslandLevel extends Level
{
	public IslandLevel() {
		name = "Island";
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
//		TeleporterBlock teleporter0 = new TeleporterBlock();
//		teleporter0.floorTex = Texture.grass;
//		teleporter0.setReference("teleporter0");
//		teleporter0.setTargetRef("teleporter1");
//		setBlock(30, 33, teleporter0);
//
//		TeleporterBlock teleporter1 = new TeleporterBlock();
//		teleporter1.floorTex = Texture.grass;
//		teleporter1.setReference("teleporter1");
//		teleporter1.setTargetRef("teleporter0");
//		setBlock(34, 32, teleporter1);
//
//		EnvironmentalEffect effct = new FireEffect(this, 9000, 2, 1);
//		addEntity(effect, 30.5, 31.5);
	}

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "graveyard", 2);
		if (id == 2) world.switchLevel(mob, "castleHall", 2);
		if (id == 3) world.switchLevel(mob, "crypt", 1);
		if (id == 4) world.switchLevel(mob, "pit", 1);
		if (id == 5) world.switchLevel(mob, "sewer", 2);
	}

	public void triggerBlock(int id) {
		if (id == 1) super.triggerBlock(13);
	}
}
