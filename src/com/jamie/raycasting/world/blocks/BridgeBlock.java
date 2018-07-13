package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class BridgeBlock extends TriggerableBlock
{
    private boolean broken = false;

	public BridgeBlock(boolean broken) {
		isOpaque = false;
		isSolid = false;
		isStatic = true;

		this.broken = broken;
        if (broken) {
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
        if (broken) {
            if (source.hasItemByName("Planks")) {
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
        broken = false;
        isUsable = false;
        isWalkable = true;
        floorTex = Texture.bridge;
    }
}
