package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SolidSpriteBlock extends Block
{
	public SolidSpriteBlock(Bitmap floorTexture, Bitmap ceilingTexture, Bitmap spriteTexture) {
		isOpaque = false;
		isSolid = true;

		floorTex = floorTexture;
		ceilTex = ceilingTexture;

		setIdleSprite(new Sprite(spriteTexture));
	}

	public SolidSpriteBlock(Bitmap floorTexture, Bitmap ceilingTexture, Sprite sprite) {
		isOpaque = false;
		isSolid = true;

		floorTex = floorTexture;
		ceilTex = ceilingTexture;

		setIdleSprite(sprite);
	}
}
