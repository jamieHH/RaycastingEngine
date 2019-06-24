package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Texture;

public class AirBlock extends Block
{
	public AirBlock() {
		isOpaque = false;
		isSolid = false;

		wallTex = Texture.none;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;
	}
}
