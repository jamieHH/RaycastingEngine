package com.jamie.raycasting.levels.blocks;

import java.util.ArrayList;
import java.util.List;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.levels.Level;

public class Block {
    public Level level;

	public boolean solidRender = false;
	public boolean blocksMotion = false;

	public Render wallTex = Texture.wall;
	public Render floorTex = Texture.floor;
	public Render ceilTex = Texture.floor;

	public int animTime = 5;
	public int spriteIndex = 0;

	public int gridX;
	public int gridZ;
	
	public int id;

	public static Block boundaryBlock = new Block();

	public List<Sprite> sprites = new ArrayList<Sprite>();

	public void tick() {

    }

    public boolean use() {
        return false;
    }
}
