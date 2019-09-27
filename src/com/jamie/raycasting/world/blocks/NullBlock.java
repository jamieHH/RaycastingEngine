package com.jamie.raycasting.world.blocks;

public class NullBlock extends Block
{
	public NullBlock() {
		isOpaque = false;
		isSolid = false;

		wallTex = null;
		floorTex = null;
		ceilTex = null;
	}
}
