package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class GrassBlock extends Block
{
	public GrassBlock() {
		isOpaque = false;
		isSolid = false;
		isStatic = true;

		floorTex = Texture.grass;
		ceilTex = Texture.floor;
	}
}
