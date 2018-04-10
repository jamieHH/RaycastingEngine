package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class GrassBlock extends Block
{
	public GrassBlock() {
		isOpaque = false;
		isSolid = false;
		isStatic = true;

		floorTex = Texture.grass;
	}
}
