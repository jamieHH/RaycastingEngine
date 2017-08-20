package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class GraveBlock extends Block {

    int i = 0;

	public GraveBlock() {
	floorTex = Texture.dirt;

		Sprite sprite = new Sprite(0, 0, 0, Texture.grave);
		sprites.add(sprite);
		
		blocksMotion = false;
	}
}
