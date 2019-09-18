package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class PillarBlock extends Block
{
	public PillarBlock() {
		isSolid = true;

		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		Bitmap[] ts0 = {
				Texture.pillar,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
