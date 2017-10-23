package com.jamie.raycasting.levels.blocks;

import java.util.ArrayList;
import java.util.List;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.levels.Level;

public class Block
{
    public Level level;

	public boolean solidRender = false;
	public boolean blocksMotion = false;
    public boolean isStatic = false;

	public Render wallTex = Texture.none;
	public Render floorTex = Texture.floor;
	public Render ceilTex = Texture.floor;

	public int animTime = 5;
	public int spriteIndex = 0;

	public int gridX;
	public int gridZ;
	
	public int id;

	private List<Sprite> sprites = new ArrayList<Sprite>();

	public Block() {

    }

	public void addSprite(Sprite s) {
		sprites.add(s);
	}

	public Sprite getSprite(int i) {
		return sprites.get(i);
	}

	public int countSprites() {
		return sprites.size();
	}

	public void clearSprites() {
		sprites.clear();
	}

	public void tick() {
		for (int i = 0; i < countSprites(); i++) {
			getSprite(i).tick();
		}
	}

    public boolean use(Mob source) {
        return false;
    }

	public void trigger() {}
}
