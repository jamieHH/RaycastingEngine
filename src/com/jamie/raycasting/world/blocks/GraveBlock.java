package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class GraveBlock extends Block
{
	public GraveBlock() {
		isSolid = false;
		isStatic = true;

		floorTex = Texture.dirt;

		Render[] ts0 = {
				Texture.grave,
		};
		addIdleSprite(new Sprite(ts0));
	}
}
