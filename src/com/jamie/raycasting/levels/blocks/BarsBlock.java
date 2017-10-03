package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BarsBlock extends Block
{
	public BarsBlock() {
		Sprite sprite = new Sprite(Texture.bars, 0, 0, 0);
		addSprite(sprite);
		
		blocksMotion = true;
	}
}
