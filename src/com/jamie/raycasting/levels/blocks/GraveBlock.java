package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class GraveBlock extends Block
{
	public GraveBlock() {
	floorTex = Texture.dirt;

		Sprite sprite = new Sprite(0, 0, 0, Texture.grave);
		addSprite(sprite);
		
		blocksMotion = false;
	}
}
