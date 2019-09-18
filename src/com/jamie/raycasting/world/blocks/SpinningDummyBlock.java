package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpinningDummyBlock extends Block
{
	public SpinningDummyBlock() {
		isSolid = true;

		floorTex = Texture.floor;
		ceilTex = Texture.floor;

	    Bitmap ts0[] = {
                Texture.spinningDummy0,
                Texture.spinningDummy1,
                Texture.spinningDummy2,
                Texture.spinningDummy3,
                Texture.spinningDummy4,
        };

		setIdleSprite(new Sprite(ts0));
	}
}
