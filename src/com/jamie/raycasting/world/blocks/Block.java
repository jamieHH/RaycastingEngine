package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;

public abstract class Block
{
	public boolean isOpaque = false;
	public boolean isSolid = false;
	public boolean isWalkable = true;
	public boolean isUsable = false;

	public Bitmap wallTex = null;
	public Bitmap floorTex = null;
	public Bitmap ceilTex = null;

	protected SpriteSet spriteSet = new SpriteSet();


	public void tick() {
		spriteSet.tick();
	}

    public boolean use(Mob source) {
        return false;
    }

    public String getActiveSetKey() {
		return spriteSet.getActiveSetKey();
	}

	public void setSpriteSet(String name, Sprite sprite) {
		spriteSet.putSet(name, sprite);
	}

	protected void runSpriteSet(String name) {
		spriteSet.runSet(name);
	}

	protected void switchSpriteSet(String name) {
		spriteSet.switchSet(name);
	}

	public Sprite getSprite() {
		return spriteSet.getSprite();
	}

	public void setIdleSprite(Sprite sprite) {
		setSpriteSet("idle", sprite);
	}
}
