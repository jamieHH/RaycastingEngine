package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;

public class AirBlock extends Block
{
	public AirBlock(Bitmap floorTexture, Bitmap ceilingTexture) {
		isOpaque = false;
		isSolid = false;

		wallTex = null;
		floorTex = floorTexture;
		ceilTex = ceilingTexture;
	}
}
