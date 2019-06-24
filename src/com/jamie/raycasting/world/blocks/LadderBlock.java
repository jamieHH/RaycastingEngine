package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.jamapp.Texture;

public class LadderBlock extends LevelPortalBlock
{
	public LadderBlock(boolean ladderUp) {
	    super();

	    Sprite sprite = new Sprite(Texture.ladder);

		if (ladderUp) {
			floorTex = Texture.floor;
			ceilTex = Texture.ladderHole;

			sprite.setOffset(0, 0.125, 0);
            setIdleSprite(sprite);
		} else {
            floorTex = Texture.ladderHole;
            ceilTex = Texture.floor;

			sprite.setOffset(0, -0.125, 0);
			setIdleSprite(sprite);
        }
	}
}
