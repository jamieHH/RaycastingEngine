package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class ButtonBlock extends Block
{
    private int useWait = 0;

	public ButtonBlock() {
		solidRender = true;
		blocksMotion = true;
		isStatic = true;

		wallTex = Texture.button0;
	}

	public boolean use() {
        if (useWait > 0) {
			wallTex = Texture.button0;
            return false;
        }

        useWait = 10;
        wallTex = Texture.button1;
        level.activateBlock(id, "?instanceType");
		return true;
	}
}
