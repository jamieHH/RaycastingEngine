package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class LampBlock extends Block
{
	public LampBlock() {
		isSolid = false;
		isStatic = true;

		floorTex = Texture.grass;
		ceilTex = Texture.floor;

		Render[] ts0 = {
				Texture.lamp,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
