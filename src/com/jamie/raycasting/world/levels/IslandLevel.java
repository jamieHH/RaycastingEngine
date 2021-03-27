package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.world.blocks.StrongDoorBlock;

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

		StrongDoorBlock b = new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), false);
		b.setReference("pitDoor");
		setBlock(12,36, b);

	}

    public void switchLevel(Entity entity, String ref) {
        if (ref.equals("1643")) world.switchLevel(entity, "graveyard", "6007");
		if (ref.equals("2017")) world.switchLevel(entity, "castleHall", "1010");
		if (ref.equals("2248")) world.switchLevel(entity, "crypt", "3312");
		if (ref.equals("3611")) world.switchLevel(entity, "pit", "2524");
		if (ref.equals("3844")) world.switchLevel(entity, "sewer", "3726");
	}

	public void triggerBlock(String ref) {
		if (ref.equals("3512")) super.triggerBlock("pitDoor");
	}
}
