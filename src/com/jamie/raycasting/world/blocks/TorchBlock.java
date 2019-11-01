package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class TorchBlock extends Block
{
	public TorchBlock(Bitmap floorTex, Bitmap ceilTex) {
		isOpaque = false;
		isSolid = false;

		wallTex = null;
		this.floorTex = floorTex;
		this.ceilTex = ceilTex;

		Bitmap[] ts0 = {
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
