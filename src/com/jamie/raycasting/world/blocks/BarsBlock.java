package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BarsBlock extends Block
{
	public BarsBlock() {
		isSolid = true;
		isStatic = true;

		Render[] ts0 = {
				Texture.bars,
		};
		addIdleSprite(new Sprite(ts0));
	}
}
