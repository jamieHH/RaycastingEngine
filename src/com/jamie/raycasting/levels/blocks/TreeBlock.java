package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class TreeBlock extends Block {
	public TreeBlock() {
		floorTex = Texture.grass;

		Sprite sprite = new Sprite(0, 0, 0, Texture.tree);
		sprites.add(sprite);
		
		blocksMotion = true;
	}
}
