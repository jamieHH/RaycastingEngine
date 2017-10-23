package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class StrongDoorBlock extends DoorBlock
{
	public StrongDoorBlock() {
	    super();
		wallTex = Texture.strongDoor;
	}

	public boolean use(Mob source) {
	    return false;
    }

    public void trigger() {
        open = !open;
    }

    public void tick() {
        super.updateOpenness();
    }
}
