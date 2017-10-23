package com.jamie.raycasting.levels;

public class CastleHallLevel extends Level
{
	public CastleHallLevel() {
	    name = "CastleHall";
	}

    public void switchLevel(int id) {
        if (id == 1) game.switchLevel("island", 1);
        if (id == 2) game.switchLevel("barracks", 1);
    }
}
