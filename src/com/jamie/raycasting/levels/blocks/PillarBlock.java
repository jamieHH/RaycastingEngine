package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class PillarBlock extends Block
{
	public PillarBlock() {
		blocksMotion = true;

		Sprite sprite = new Sprite(Texture.pillar);
		addSprite(sprite);

	}
}
