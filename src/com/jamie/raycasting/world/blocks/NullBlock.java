package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class NullBlock extends Block
{
	public NullBlock() {
		isOpaque = false;
		isSolid = false;

		wallTex = null;
		floorTex = null;
		ceilTex = null;
	}
}
