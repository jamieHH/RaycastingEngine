package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Texture;

public class ShrubsBlock extends Block
{
	public ShrubsBlock() {
		isOpaque = true;
		isSolid = true;

		floorTex = Texture.grass;
		wallTex = Texture.leaves;
	}
}
