package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class GraveBlock extends Block
{
	public GraveBlock() {
		isSolid = false;

		floorTex = Texture.dirt;

		Bitmap[] ts0 = {
				Texture.grave,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
