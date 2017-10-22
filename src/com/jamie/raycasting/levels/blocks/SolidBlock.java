package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class SolidBlock extends Block
{
	public SolidBlock() {
		solidRender = true;
		blocksMotion = true;
		isStatic = true;

		wallTex = Texture.wall;
	}
}
