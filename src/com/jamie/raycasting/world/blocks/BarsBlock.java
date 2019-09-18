package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BarsBlock extends Block
{
	public BarsBlock() {
		isSolid = true;

		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		Bitmap[] ts0 = {
				Texture.bars,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
