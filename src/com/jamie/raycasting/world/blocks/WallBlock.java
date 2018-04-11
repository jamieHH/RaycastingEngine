package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class WallBlock extends Block
{
	public WallBlock() {
		isOpaque = true;
		isSolid = true;
		isStatic = true;

		wallTex = Texture.wall;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;
	}
}
