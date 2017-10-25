package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.entities.particles.DropParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FountainBlock extends Block
{
    private int i = 0;

	public FountainBlock() {
		blocksMotion = true;
		floorTex = Texture.grass;

		Sprite sprite = new Sprite(Texture.fountain);
		addSprite(sprite);
	}

    public void tick() {

	    if (i > 0) {
	        i--;
	        return;
        }

        DropParticle dropParticle = new DropParticle((gridX * 16) + 8, ((gridZ * 16) + 8));
        level.addEntity(dropParticle);

        i = 10;
    }
}
