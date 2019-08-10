package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.world.blocks.TurretBlock;

public class DungeonLevel extends Level
{
	public DungeonLevel() {
		name = "Dungeon";
	}

	protected void postCreate() {
		setBlock(32, 32, new TurretBlock("N"));
	}

	public void switchLevel(Mob mob, int id) {

	}

	public void triggerBlock(int id) {
		if (id == 1) super.triggerBlock(0);
	}
}