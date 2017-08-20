package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BarsBlock extends Block {
	public BarsBlock() {
		Sprite sprite = new Sprite(0, 0, 0, Texture.bars);
		sprites.add(sprite);
		
		blocksMotion = true;
	}
}
