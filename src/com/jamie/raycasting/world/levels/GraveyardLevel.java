package com.jamie.raycasting.world.levels;

public class GraveyardLevel extends Level
{
	public GraveyardLevel() {
		name = "Graveyard";
		height = 8192;
	}

    public void switchLevel(int id) {
        if (id == 3) world.switchLevel("island", 1);
    }
}
