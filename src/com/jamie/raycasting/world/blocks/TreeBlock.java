package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class TreeBlock extends Block
{
	public TreeBlock() {
		isSolid = true;

		floorTex = Texture.grass;

		Bitmap[] ts0 = {
				Texture.tree,
		};
		setIdleSprite(new Sprite(ts0));
	}
}
