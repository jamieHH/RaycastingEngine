package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class BridgeBlock extends TriggerableBlock
{
    private boolean isBroken;

	public BridgeBlock(boolean isBroken) {
		isOpaque = false;
		isSolid = false;

		this.isBroken = isBroken;
        if (getState()) {
            isUsable = true;
            isWalkable = false;
            floorTex = Texture.bridgeWaterBroken;
        } else {
            isUsable = false;
            isWalkable = true;
            floorTex = Texture.bridge;
        }
	}

    public boolean use(Mob source) {
        if (getState()) {
            if (source.getItemByName("Planks") != null) {
                source.getItemByName("Planks").remove();
                trigger();
                source.addHudHeading("The bridge is mended");
                return true;
            }

            source.addHudHeading("You need some planks");
        }

        return false;
    }

    public void trigger() {
        setState(false);
    }

    public boolean getState() {
        return isBroken;
    }

    public void setState(boolean state) {
	    if (state) {
            isBroken = true;
            isUsable = true;
            isWalkable = false;
            floorTex = Texture.bridgeWaterBroken;
        } else {
            isBroken = false;
            isUsable = false;
            isWalkable = true;
            floorTex = Texture.bridge;
        }
    }
}
