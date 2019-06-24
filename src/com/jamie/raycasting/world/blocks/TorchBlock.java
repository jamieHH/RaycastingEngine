package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.jamapp.Texture;

public class TorchBlock extends Block
{
	public TorchBlock() {
		isOpaque = false;
		isSolid = false;

		wallTex = Texture.none;
		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		Render[] ts0 = {
				Texture.torch0,
				Texture.torch1,
				Texture.torch2,
				Texture.torch0,
				Texture.torch1,
				Texture.torch0,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
