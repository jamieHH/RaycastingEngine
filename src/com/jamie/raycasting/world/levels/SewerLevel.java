package com.jamie.raycasting.world.levels;

public class SewerLevel extends Level
{
	public SewerLevel() {
	    name = "Sewer";
	}

    public void switchLevel(int id) {
        if (id == 1) game.switchLevel("prison", 1);
        if (id == 2) game.switchLevel("island", 2);
    }
}
