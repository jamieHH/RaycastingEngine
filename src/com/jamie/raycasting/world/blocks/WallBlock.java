package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Texture;

public class WallBlock extends Block
{
	public WallBlock() {
		isOpaque = true;
		isSolid = true;

		wallTex = Texture.wall;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;
	}
}
