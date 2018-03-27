package com.jamie.raycasting.world.levels;

public class BarracksLevel extends Level
{
	public BarracksLevel() {
		name = "Barracks";
	}

	public void switchLevel(int id) {
		if (id == 1) game.switchLevel("castleHall", 2);
	}

    public void activateBlock(int id) {
        if (id == 1) super.activateBlock(1);
    }
}
