package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.jamapp.Texture;

public class BarsBlock extends Block
{
	public BarsBlock() {
		isSolid = true;

		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		Render[] ts0 = {
				Texture.bars,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
