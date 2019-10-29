package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class StrongDoorBlock extends DoorBlock
{
	public StrongDoorBlock(boolean open) {
	    super(open);

		wallTex = Texture.strongDoor;
	}

	public boolean use(Mob source) {
	    if (!getState()) {
            source.addHudHeading("The door wont budge");
        }
	    return false;
    }

    public void tick() {
        super.updateOpenness();
    }
}
