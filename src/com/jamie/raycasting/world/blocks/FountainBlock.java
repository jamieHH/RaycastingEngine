package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.particles.DropParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FountainBlock extends Block
{
    private int i = 0;

	public FountainBlock() {
		isSolid = true;
		floorTex = Texture.grass;

		Render[] ts0 = {
				Texture.fountain,
		};
		setIdleSprite(new Sprite(ts0));
	}

    public void tick() {

	    if (i > 0) {
	        i--;
	        return;
        }

        DropParticle dropParticle = new DropParticle(gridX + 0.5, gridZ + 0.5);
        level.addEntity(dropParticle);

        i = 10;
    }
}
