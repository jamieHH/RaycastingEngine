package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class SolidBlock extends Block
{
	public SolidBlock() {
		isOpaque = true;
		isSolid = true;
		isStatic = true;

		wallTex = Texture.wall;
	}
}
