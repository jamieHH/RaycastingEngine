package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class DoorBlock extends TriggerableBlock
{
	private boolean isOpen;

	public double openness = 0;
	private double openLimit = 7 / 8.0;


	public DoorBlock(boolean isOpen) {
		isOpaque = false;
		isSolid = true;

        wallTex = Texture.door;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		this.isOpen = isOpen;
	}

	public boolean use(Mob source) {
        trigger();

	    return true;
    }

    public void trigger() {
        setState(!getState());
    }

    public void tick() {
	    super.tick();
        updateOpenness();
    }

    protected void updateOpenness() {
        if (isOpen) openness += 0.1;
        else openness -= 0.1;
        if (openness < 0) openness = 0;
        if (openness > 1) openness = 1;

        if (openness < openLimit && !isOpen && !isSolid) {
            if (level.blockContainsEntity(gridX, gridZ)) {
                openness = openLimit;
                return;
            }
        }

        isSolid = openness < openLimit;
    }

    public boolean getState() {
        return isOpen;
    }

    public void setState(boolean state) {
        if (state) {
            isOpen = true;
            emitSound(Sound.slideUp);
        } else {
            emitSound(Sound.slideDown);
            isOpen = false;
        }
    }
}
