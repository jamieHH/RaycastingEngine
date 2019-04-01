package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.environmentalEffects.EnvironmentalEffect;
import com.jamie.raycasting.entities.environmentalEffects.FireEffect;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.world.blocks.TeleporterBlock;

public class IslandLevel extends Level
{
	public IslandLevel() {
		name = "Island";
		height = 8192;
		defaultFloorBlock = GrassBlock;
	}

	protected void postCreate() {
		TeleporterBlock teleporter0 = new TeleporterBlock();
		teleporter0.floorTex = Texture.grass;
		teleporter0.setReference("teleporter0");
		teleporter0.setTargetRef("teleporter1");
		setBlock(33, 30, teleporter0);

		TeleporterBlock teleporter1 = new TeleporterBlock();
		teleporter1.floorTex = Texture.grass;
		teleporter1.setReference("teleporter1");
		teleporter1.setTargetRef("teleporter0");
		setBlock(32, 34, teleporter1);

		EnvironmentalEffect effect = new FireEffect(this, 9000, 2, 1);
		addEntity(effect, 30.5, 31.5);
	}

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "graveyard", 2);
		if (id == 2) world.switchLevel(mob, "castleHall", 1);
		if (id == 3) world.switchLevel(mob, "crypt", 1);
		if (id == 4) world.switchLevel(mob, "sewer", 2);
    }
}
