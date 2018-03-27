package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class GraveBlock extends Block
{
	public GraveBlock() {
		isSolid = false;
		isStatic = true;

		floorTex = Texture.dirt;

		Sprite sprite = new Sprite(Texture.grave);
		addSprite(sprite);
	}
}
