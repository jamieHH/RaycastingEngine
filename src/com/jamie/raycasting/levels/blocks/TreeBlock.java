package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class TreeBlock extends Block
{
	public TreeBlock() {
		isSolid = true;
		isStatic = true;

		floorTex = Texture.grass;

		Sprite sprite = new Sprite(Texture.tree);
		addSprite(sprite);

	}
}
