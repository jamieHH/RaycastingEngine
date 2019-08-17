package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BarrelsBlock extends Block
{
	public BarrelsBlock() {
		isSolid = false;

		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		Render[] ts0 = {
				Texture.barrel0,
		};
		Sprite s = new Sprite(ts0);
		s.setOffset(0.2, 0, 0.2);
		setIdleSprite(s);
	}
}
