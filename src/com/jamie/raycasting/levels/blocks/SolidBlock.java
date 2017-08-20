package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.levels.blocks.Block;

public class SolidBlock extends Block {
	public SolidBlock() {
		solidRender = true;
		blocksMotion = true;

		wallTex = Texture.wall;
	}
}
