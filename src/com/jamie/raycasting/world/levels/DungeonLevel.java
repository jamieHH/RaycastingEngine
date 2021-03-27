package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.BouncingEntity;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
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

		Bitmap[] ts0 = {
				Texture.spinningDummy0,
				Texture.spinningDummy1,
				Texture.spinningDummy2,
				Texture.spinningDummy3,
				Texture.spinningDummy4,
		};
		addEntity(new BouncingEntity(new Sprite(ts0), "N", 0.1, 1),22.5, 40.5);
	}

	public void switchLevel(Entity entity, String ref) {

	}

	public void triggerBlock(String ref) {
		if (ref.equals("3035")) super.triggerLogic("timer");
	}
}