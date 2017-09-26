package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpinningDummyBlock extends Block
{
	private Sprite sprite;
	
	public SpinningDummyBlock() {
		sprite = new Sprite(0, 0, 0, Texture.spinningDummy);
		addSprite(sprite);
	}
}
