package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class GraveBlock extends Block
{
	public GraveBlock() {
		blocksMotion = false;

		floorTex = Texture.dirt;

		Sprite sprite = new Sprite(Texture.grave);
		addSprite(sprite);
	}
}
