package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BarsBlock extends Block
{
	public BarsBlock() {
		isSolid = true;
		isStatic = true;

		Sprite sprite = new Sprite(Texture.bars);
		addSprite(sprite);
	}
}
