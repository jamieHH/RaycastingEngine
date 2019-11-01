package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class StrongDoorBlock extends DoorBlock
{
	public StrongDoorBlock(Bitmap floorTex, Bitmap ceilTex, boolean isOpen) {
	    super(floorTex, ceilTex, isOpen);

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
