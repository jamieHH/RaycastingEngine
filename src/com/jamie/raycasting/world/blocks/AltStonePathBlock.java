package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class AltStonePathBlock extends Block
{
	public AltStonePathBlock() {
		isOpaque = false;
		isSolid = false;

		floorTex = Texture.altStonePath;
		ceilTex = Texture.floor;
	}
}
