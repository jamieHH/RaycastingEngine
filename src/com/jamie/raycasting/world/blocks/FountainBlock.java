package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.particles.DropParticle;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FountainBlock extends FunctionBlock
{
    private int particleWait = 0;
    private int particleTicks;
    private boolean isActive = true;

	public FountainBlock(boolean isActive) {
		isSolid = true;

		floorTex = Texture.grass;

		Bitmap[] ts0 = {
				Texture.fountain,
		};
		setIdleSprite(new Sprite(ts0));

		this.isActive = isActive;
	}

    public void tick() {
		if (isActive) {
			if (particleTicks > 0) {
				particleTicks--;
			} else {
				DropParticle p = new DropParticle(1);
				level.addEntity(p, gridX + 0.5, gridZ + 0.5);

				particleTicks = particleWait;
			}
		}
    }

	protected void setState(boolean state) {
		if (state) {
			isActive = true;
		} else {
			isActive = false;
		}
	}
}
