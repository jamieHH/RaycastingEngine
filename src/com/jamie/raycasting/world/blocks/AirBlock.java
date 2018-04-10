package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class AirBlock extends Block
{
	public AirBlock() {
		isOpaque = false;
		isSolid = false;
		isStatic = true;

		wallTex = Texture.none;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;
	}
}
