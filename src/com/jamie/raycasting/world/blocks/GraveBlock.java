package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.jamapp.Texture;

public class GraveBlock extends Block
{
	public GraveBlock() {
		isSolid = false;

		floorTex = Texture.dirt;

		Render[] ts0 = {
				Texture.grave,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
