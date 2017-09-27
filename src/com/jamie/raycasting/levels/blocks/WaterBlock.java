package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;

public class WaterBlock extends Block
{
    protected int animTime = 20;
    protected int floorTexIndex = 0;
    public ArrayList<Render> floorTextures = new ArrayList<>();

	public WaterBlock() {
		solidRender = false;
		blocksMotion = true;

		floorTex = Texture.water0;
		ceilTex = Texture.floor;

		floorTextures.add(Texture.water0);
		floorTextures.add(Texture.water1);
		floorTextures.add(Texture.water2);
	}

    public void texTick() {
        if (animTime > 0) {
            animTime--;
            return;
        }

        int i = floorTexIndex;
        i++;
        if (i >= floorTextures.size()) {
            i = 0;
        }

        floorTexIndex = i;
        animTime = 20;
        floorTex = floorTextures.get(floorTexIndex);
    }

	public void tick() {
        texTick();
    }
}
