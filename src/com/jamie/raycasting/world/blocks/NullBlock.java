package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class NullBlock extends Block
{
	public NullBlock() {
		isOpaque = false;
		isSolid = false;

		wallTex = Texture.none;
		floorTex = Texture.none;
		ceilTex = Texture.none;
	}
}
