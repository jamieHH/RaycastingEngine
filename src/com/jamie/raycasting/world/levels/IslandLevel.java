package com.jamie.raycasting.world.levels;

public class IslandLevel extends Level
{
	public IslandLevel() {
		name = "Island";
		height = 8192;
	}

    public void switchLevel(int id) {
        if (id == 1) world.switchLevel("castleHall", 1);
        if (id == 2) world.switchLevel("sewer", 2);
    }
}
