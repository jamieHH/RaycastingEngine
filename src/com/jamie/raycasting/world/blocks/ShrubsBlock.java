package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class ShrubsBlock extends Block
{
	public ShrubsBlock() {
		isOpaque = true;
		isSolid = true;

		floorTex = Texture.grass;
		wallTex = Texture.leaves;
	}
}
