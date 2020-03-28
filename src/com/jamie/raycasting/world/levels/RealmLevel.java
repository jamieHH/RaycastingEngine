package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Entity;

public class RealmLevel extends Level
{
	public RealmLevel() {
		name = "Realm";
		height = 8192;
		isOutside = true;
	}

	protected void postCreate() {

	}

	public void switchLevel(Entity entity, int id) {

	}
}
