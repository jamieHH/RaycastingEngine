package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.StoneParticle;
import com.jamie.raycasting.graphics.Texture;

public class CrackedWallBlock extends TriggerableBlock
{
	public CrackedWallBlock(Bitmap floorTex, Bitmap ceilTex) {
        this.isOpaque = true;
        this.isSolid = true;

        this.wallTex = Texture.wallCracked;
        this.floorTex = floorTex;
        this.ceilTex = ceilTex;
	}

    public void trigger() {
        StoneParticle p = new StoneParticle(12);
        this.level.addEntity(p, gridX + 0.5, gridZ + 0.5);

        this.level.setBlock(gridX, gridZ, this.level.AirBlock);
    }

    public boolean use(Mob source) {
        source.addHudHeading("This wall is weak");
        return false;
    }

    public boolean getState() {
        return false;
    }

    public void setState(boolean state) {

    }
}
