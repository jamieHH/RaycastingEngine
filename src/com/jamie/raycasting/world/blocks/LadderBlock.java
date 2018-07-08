package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class LadderBlock extends LevelPortalBlock
{
	public LadderBlock(boolean ladderUp) {
	    super();

		if (ladderUp) {
			floorTex = Texture.floor;
			ceilTex = Texture.ladderHole;

            setIdleSprite(new Sprite(Texture.ladder, 0, 0.125, 0));
		} else {
            floorTex = Texture.ladderHole;
            ceilTex = Texture.floor;

            setIdleSprite(new Sprite(Texture.ladder, 0, -0.125, 0));
        }
	}
}
