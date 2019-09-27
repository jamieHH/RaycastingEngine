package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;

public class SolidBlock extends Block
{
	public SolidBlock(Bitmap wallTexture) {
		isOpaque = true;
		isSolid = true;

		wallTex = wallTexture;
		floorTex = null;
		ceilTex = null;
	}
}
