package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class DoorBlock extends Block
{
	private boolean open = false;

    private int useTicks = 0;
    private int useWait = 10;

	public double openness = 0;
	private double openLimit = 7 / 8.0;

	public DoorBlock() {
		solidRender = false;
		blocksMotion = true;
		wallTex = Texture.door;
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
    }

    public void tick() {
	    if (useTicks > 0) useTicks--;
        updateOpenness();
    }

    private void updateOpenness() {
        if (open) openness += 0.1;
        else openness -= 0.1;
        if (openness < 0) openness = 0;
        if (openness > 1) openness = 1;

        if (openness < openLimit && !open && !blocksMotion) {
            if (level.blockContainsEntity(gridX, gridZ)) {
                openness = openLimit;
                return;
            }
        }

        blocksMotion = openness < openLimit;
    }
}
