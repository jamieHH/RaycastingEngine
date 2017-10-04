package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpinningDummyBlock extends Block
{
	private Sprite sprite;
	
	public SpinningDummyBlock() {
		sprite = new Sprite(Texture.spinningDummy);
		addSprite(sprite);
	}
}
