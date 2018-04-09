package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class PillarBlock extends Block
{
	public PillarBlock() {
		isSolid = true;
		isStatic = true;

		Render[] ts0 = {
				Texture.pillar,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
