package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class StonePathBlock extends Block
{
	public StonePathBlock() {
		isOpaque = false;
		isSolid = false;
		isStatic = true;

		floorTex = Texture.stonePath;
		ceilTex = Texture.floor;
	}
}