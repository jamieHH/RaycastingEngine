package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class ButtonBlock extends Block
{
    private int useTicks = 0;
    private int useWait = 10;

	public ButtonBlock() {
		solidRender = true;
		blocksMotion = true;

		wallTex = Texture.wallButton0;
	}

	public void tick() {
        if (useTicks > 0) {
            useTicks--;
        } else {
            wallTex = Texture.wallButton0;
        }
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
        wallTex = Texture.wallButton1;
        level.activateBlock(id, "?instanceType");
    }
}
