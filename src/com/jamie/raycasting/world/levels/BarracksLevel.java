package com.jamie.raycasting.world.levels;

public class BarracksLevel extends Level
{
	public BarracksLevel() {
		name = "Barracks";
	}

	public void switchLevel(int id) {
		if (id == 1) world.switchLevel("castleHall", 2);
	}

    public void triggerBlock(int id) {
        if (id == 1) super.triggerBlock(1);
    }
}
