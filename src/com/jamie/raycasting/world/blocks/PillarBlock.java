package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.jamapp.Texture;

public class PillarBlock extends Block
{
	public PillarBlock() {
		isSolid = true;

		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		Render[] ts0 = {
				Texture.pillar,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
