package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.particles.DropParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FountainBlock extends FunctionBlock
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
        } else {
			i = 5;

			DropParticle p = new DropParticle();
			level.addEntity(p, gridX + 0.5, gridZ + 0.5);

		}
    }
}
