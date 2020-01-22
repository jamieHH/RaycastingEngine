package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.mobs.Mob;

public class RealmLevel extends Level
{
	public RealmLevel() {
		name = "Realm";
		height = 8192;
		isOutside = true;
	}

	protected void postCreate() {

	}

	public void switchLevel(Mob mob, int id) {

	}
}
