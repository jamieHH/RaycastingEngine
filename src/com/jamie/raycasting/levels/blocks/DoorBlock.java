package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class DoorBlock extends Block
{
	public boolean open = false;

    public int useWait = 0;

	public double openness = 0;
	public double openLimit = 7 / 8.0;

	public DoorBlock() {
		solidRender = false;
		blocksMotion = true;
		wallTex = Texture.door;
	}

	public boolean use() {
        if (useWait > 0) {
            return false;
        }

        System.out.println("activate");
        // TODO: find out how doors get activated faster by mobs.

        useWait = 10;
	    open = !open;
	    return true;
    }

    public void tick() {
	    if (useWait > 0) useWait--;
        updateOpenness();
    }

    public void updateOpenness() {
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
