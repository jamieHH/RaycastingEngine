package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class DoorBlock extends TriggerableBlock
{
	private boolean open;

    private int useTicks = 0;
    private int useWait = 10;

	public double openness = 0;
	private double openLimit = 7 / 8.0;


	public DoorBlock(boolean open) {
		isOpaque = false;
		isSolid = true;

        wallTex = Texture.door;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		this.open = open;
	}

	public boolean use(Mob source) {
        if (useTicks > 0) {
            return false;
        }
        useTicks = useWait;

        trigger();
	    return true;
    }

    public void trigger() {
        setState(!getState());
    }

    public void tick() {
	    super.tick();
	    if (useTicks > 0) useTicks--;
        updateOpenness();
    }

    protected void updateOpenness() {
        if (open) openness += 0.1;
        else openness -= 0.1;
        if (openness < 0) openness = 0;
        if (openness > 1) openness = 1;

        if (openness < openLimit && !open && !isSolid) {
            if (level.blockContainsEntity(gridX, gridZ)) {
                openness = openLimit;
                return;
            }
        }

        isSolid = openness < openLimit;
    }

    public boolean getState() {
        return open;
    }

    private void setState(boolean state) {
        if (state) {
            open = true;
            emitSound(Sound.slideUp);
        } else {
            emitSound(Sound.slideDown);
            open = false;
        }
    }
}
