package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class GrassBlock extends Block
{
	public GrassBlock() {
		solidRender = false;
		blocksMotion = false;

		floorTex = Texture.grass;
		ceilTex = Texture.floor;
	}
}
