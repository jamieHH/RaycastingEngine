package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class ShrubsBlock extends Block
{
	public ShrubsBlock() {
		isOpaque = true;
		isSolid = true;
		isStatic = true;

		floorTex = Texture.grass;
		wallTex = Texture.leaves;
	}
}
