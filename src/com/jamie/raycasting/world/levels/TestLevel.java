package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.*;
import com.jamie.raycasting.input.ArtificialInputHandler;

public class TestLevel extends Level
{
	public TestLevel() {
		name = "Test";
		height = 2;
	}

	protected void postCreate() {
		addEntity(new Imp(new ArtificialInputHandler()), 30.5, 38.5);
	}

	public void switchLevel(Entity entity, int id) {

	}
}
