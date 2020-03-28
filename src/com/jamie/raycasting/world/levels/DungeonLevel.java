package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.BouncingEntity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.world.LogicRelay;
import com.jamie.raycasting.world.LogicTimer;
import com.jamie.raycasting.world.blocks.TurretBlock;

public class DungeonLevel extends Level
{
	public DungeonLevel() {
		name = "Dungeon";
	}

	protected void postCreate() {
		TurretBlock b = new TurretBlock("N");
		b.setReference("turret");
		setBlock(35, 29, b);
		setBlock(23, 40, SignBlock);

		addLogic(new LogicRelay("relay", new String[] {"turret"}));
		addLogic(new LogicTimer("timer", "turret", 10));

		addEntity(new BouncingEntity(),22.5, 40.5);
	}

	public void switchLevel(Mob mob, int id) {

	}

	public void triggerBlock(int id) {
//		if (id == 1) super.triggerBlock(0);
//		if (id == 1) super.triggerBlock("turret");
		if (id == 1) super.triggerLogic("timer");
	}
}