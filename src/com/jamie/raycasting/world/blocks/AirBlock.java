package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class AirBlock extends Block
{
	public AirBlock() {
		isOpaque = false;
		isSolid = false;

		wallTex = null;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;
	}
}
