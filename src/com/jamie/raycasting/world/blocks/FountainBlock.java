package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.particles.DropParticle;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class FountainBlock extends FunctionBlock
{
    private static final int PARTICLE_WAIT = 1;
    private int particleTicks;
    private boolean isActive;

	public FountainBlock(Bitmap ceilTex, boolean isActive) {
		this.isSolid = true;

		this.floorTex = Texture.grass;
		this.ceilTex = ceilTex;

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

				particleTicks = PARTICLE_WAIT;
			}
		}
    }

	protected void setState(boolean state) {
        isActive = state;
	}
}
