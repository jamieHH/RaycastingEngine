package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpinningDummyBlock extends Block
{
	public SpinningDummyBlock() {
	    Render ts[] = {
                Texture.spinningDummy0,
                Texture.spinningDummy1,
                Texture.spinningDummy2,
                Texture.spinningDummy3,
                Texture.spinningDummy4,
        };

		Sprite sprite = new Sprite(ts);
		addSprite(sprite);
	}
}
