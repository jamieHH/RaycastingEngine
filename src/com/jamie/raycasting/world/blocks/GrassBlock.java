package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Texture;

public class GrassBlock extends Block
{
	public GrassBlock() {
		isOpaque = false;
		isSolid = false;

		floorTex = Texture.grass;

		ceilTex = Texture.noTex;
	}
}
