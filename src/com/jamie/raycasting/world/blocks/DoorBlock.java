package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class DoorBlock extends TriggerableBlock
{
	protected boolean open = false;

    private int useTicks = 0;
    private int useWait = 10;

	public double openness = 0;
	private double openLimit = 7 / 8.0;


	public DoorBlock() {
		isOpaque = false;
		isSolid = true;

        wallTex = Texture.door;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;
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
        open = !open;
        if (open) {
            Sound.slideDown.play();
        } else {
            Sound.slideUp.play();
        }
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
            if (level.blockContainsEntity(gridZ, gridX)) {
                openness = openLimit;
                return;
            }
        }

        isSolid = openness < openLimit;
    }
}
