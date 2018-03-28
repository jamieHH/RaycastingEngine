package com.jamie.raycasting.world.levels;

public class CastleHallLevel extends Level
{
	public CastleHallLevel() {
	    name = "CastleHall";
	}

    public void switchLevel(int id) {
        if (id == 1) world.switchLevel("island", 1);
        if (id == 2) world.switchLevel("barracks", 1);
    }
}
