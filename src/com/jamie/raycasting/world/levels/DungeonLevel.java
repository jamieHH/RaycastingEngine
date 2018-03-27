package com.jamie.raycasting.world.levels;

public class DungeonLevel extends Level
{
	public DungeonLevel() {
		name = "Prison";
	}

    public void switchLevel(int id) {
        if (id == 1) game.switchLevel("prison", 1);
    }
}
