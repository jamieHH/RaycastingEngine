package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.world.levels.Level;

public class Block
{
    public Level level;

	public boolean isOpaque = false;
	public boolean isSolid = false;
	public boolean isWalkable = true;

	public boolean isStatic = false;

	public Render wallTex = Texture.none;
	public Render floorTex = Texture.none;
	public Render ceilTex = Texture.none;

	public int gridX;
	public int gridZ;
	public String reference;
	
	public int id;

	public int height = 1;

	private SpriteSet spriteSet = new SpriteSet();

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

	public void setReference(String reference) {
		this.reference = reference;
	}
}
