package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class PillarBlock extends Block
{
	public PillarBlock() {
		Sprite sprite = new Sprite(0, 0, 0, Texture.pillar);
		addSprite(sprite);
		
		blocksMotion = true;
	}
}
