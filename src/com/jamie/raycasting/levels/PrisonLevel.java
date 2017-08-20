package com.jamie.raycasting.levels;

import java.util.ArrayList;

public class PrisonLevel extends Level {
	public PrisonLevel() {
		name = "Prison";
	}

    public void switchLevel(int id) {
        if (id == 1) game.switchLevel("sewer", 1);
        if (id == 2) game.switchLevel("dungeon", 1);
    }
}
