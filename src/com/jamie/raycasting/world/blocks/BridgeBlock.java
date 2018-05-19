package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Texture;

public class BridgeBlock extends Block
{
	public BridgeBlock() {
		isOpaque = false;
		isSolid = false;
		isStatic = true;

		floorTex = Texture.bridge;
	}
}
