package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.environmentalEffects.EnvironmentalEffect;
import com.jamie.raycasting.entities.environmentalEffects.FireEffect;
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
		teleporter0.setReference("teleporter0");
		teleporter0.setTargetRef("teleporter1");
		setBlock(30, 33, teleporter0);

		TeleporterBlock teleporter1 = new TeleporterBlock();
		teleporter1.floorTex = Texture.grass;
		teleporter1.setReference("teleporter1");
		teleporter1.setTargetRef("teleporter0");
		setBlock(34, 32, teleporter1);

		EnvironmentalEffect effect = new FireEffect(this, 9000, 2, 1);
		effect.setPosition(30.5, 31.5);
		addEntity(effect);
	}

    public void switchLevel(int id) {
        if (id == 1) world.switchLevel("castleHall", 1);
        if (id == 2) world.switchLevel("sewer", 2);
    }
}
