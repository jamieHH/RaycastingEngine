package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BarsBlock extends Block
{
	public BarsBlock() {
		blocksMotion = true;

		Sprite sprite = new Sprite(Texture.bars);
		addSprite(sprite);
	}
}
