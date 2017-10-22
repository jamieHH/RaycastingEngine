package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class DoorBlock extends Block
{
	private boolean open = false;

    private int useWait = 0;

	public double openness = 0;
	private double openLimit = 7 / 8.0;

	public DoorBlock() {
		solidRender = false;
		blocksMotion = true;
		wallTex = Texture.door;
	}

	public boolean use() {
        if (useWait > 0) {
            return false;
        }

        useWait = 10;
        trigger();
	    return true;
    }

    public void trigger() {
        open = !open;
    }

    public void tick() {
	    if (useWait > 0) useWait--;
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
