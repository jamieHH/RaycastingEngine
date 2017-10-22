package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class ButtonBlock extends Block
{
    private int useWait = 0;

	public ButtonBlock() {
		solidRender = true;
		blocksMotion = true;

		wallTex = Texture.button0;
	}

	public void tick() {
        if (useWait > 0) {
            useWait--;
        } else if (useWait == 0) {
            wallTex = Texture.button0;
        }
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
        wallTex = Texture.button1;
        level.activateBlock(id, "?instanceType");
    }
}
