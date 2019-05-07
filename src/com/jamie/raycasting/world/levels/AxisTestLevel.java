package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.items.weapons.KnifeWeapon;

public class AxisTestLevel extends Level
{
	public AxisTestLevel() {
		name = "axisTest";
	}

	protected void postCreate() {
		Drop d1 = new Drop(new KnifeWeapon());
		addEntity(d1, 2.5, 0.5);
	}

	public void switchLevel(Mob mob, int id) {

	}
}