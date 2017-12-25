package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Texture;

public class NullBlock extends Block
{
	public NullBlock() {
	    super();
		isOpaque = false;
		isSolid = false;
		isStatic = true;

		wallTex = Texture.none;
		floorTex = Texture.none;
		ceilTex = Texture.none;
	}
}
