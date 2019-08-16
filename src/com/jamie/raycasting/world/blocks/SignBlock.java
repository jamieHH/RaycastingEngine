package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SignBlock extends Block
{
	public SignBlock() {
		isSolid = false;

		floorTex = Texture.grass;
		ceilTex = Texture.floor;

		Render[] ts0 = {
				Texture.signN,
				Texture.signW,
				Texture.signS,
				Texture.signE,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
