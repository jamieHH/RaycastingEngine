package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class StonePathBlock extends Block {
	public StonePathBlock() {
		solidRender = false;
		blocksMotion = false;

		floorTex = Texture.stonePath;
		ceilTex = Texture.floor;
	}
}
