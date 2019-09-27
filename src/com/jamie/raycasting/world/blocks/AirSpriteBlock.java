package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;

public class AirSpriteBlock extends Block
{
	public AirSpriteBlock(Bitmap floorTexture, Bitmap ceilingTexture, Bitmap spriteTexture) {
		isOpaque = false;
		isSolid = false;

		floorTex = floorTexture;
		ceilTex = ceilingTexture;

		setIdleSprite(new Sprite(spriteTexture));
	}

	public AirSpriteBlock(Bitmap floorTexture, Bitmap ceilingTexture, Sprite sprite) {
		isOpaque = false;
		isSolid = false;

		wallTex = null;
		floorTex = floorTexture;
		ceilTex = ceilingTexture;

		setIdleSprite(sprite);

	}
}
