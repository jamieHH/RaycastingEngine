package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class CeilDripBlock extends Block
{
	public CeilDripBlock() {
        isSolid = false;

        floorTex = Texture.wetFloor;
        ceilTex = Texture.wetFloor;

		Render[] ts0 = {
                Texture.drip0,
                Texture.drip0,
                Texture.drip1,
                Texture.drip1,
                Texture.drip2,
                Texture.drip3,
                Texture.drip4,
                Texture.drip0,
		};

        setIdleSprite(new Sprite(ts0));
	}
}
