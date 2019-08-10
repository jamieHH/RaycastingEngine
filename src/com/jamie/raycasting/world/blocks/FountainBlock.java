package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.particles.DropParticle;
import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FountainBlock extends FunctionBlock
{
    private int particleWait = 0;
    private int particleTicks;
    private boolean isActive = true;

	public FountainBlock() {
		isSolid = true;

		floorTex = Texture.grass;

		Render[] ts0 = {
				Texture.fountain,
		};
		setIdleSprite(new Sprite(ts0));
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
}
