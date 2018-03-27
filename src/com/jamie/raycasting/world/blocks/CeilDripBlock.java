package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class CeilDripBlock extends Block
{
//    public int animTime = 5;
//    public int spriteIndex = 0;

	public CeilDripBlock() {
        isSolid = false;

        floorTex = Texture.wetFloor;
        ceilTex = Texture.wetFloor;

		Render[] ts = {
                Texture.drip0,
                Texture.drip0,
                Texture.drip1,
                Texture.drip1,
                Texture.drip2,
                Texture.drip3,
                Texture.drip4,
                Texture.drip0,
		};

        Sprite sprite = new Sprite(ts);
        addSprite(sprite);
	}
}
