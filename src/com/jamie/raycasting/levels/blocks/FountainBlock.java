package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.entities.particles.DropParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FountainBlock extends Block
{
    private int i = 0;

	public FountainBlock() {
		floorTex = Texture.grass;

		Sprite sprite = new Sprite(0, 0, 0, Texture.fountain);
		addSprite(sprite);
		
		blocksMotion = true;
	}

    public void tick() {

	    if (i > 0) {
	        i--;
	        return;
        }

        DropParticle dropParticle = new DropParticle((gridX * 16) + 8, ((gridZ * 16) + 8));
        dropParticle.level = level;
        level.addEntity(dropParticle);

        i = 10;
    }
}
