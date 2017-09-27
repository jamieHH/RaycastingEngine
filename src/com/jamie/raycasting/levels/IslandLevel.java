package com.jamie.raycasting.levels;

public class IslandLevel extends Level
{
	public IslandLevel() {
		name = "Island";
		levelHeight = 8192;
	}

    public void switchLevel(int id) {
        if (id == 1) game.switchLevel("castleHall", 1);
        if (id == 2) game.switchLevel("sewer", 2);
    }
}
