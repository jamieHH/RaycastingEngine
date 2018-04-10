package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;

public class WaterBlock extends Block
{
    private int animTime = 20;
    private int floorTexIndex = 0;

    private ArrayList<Render> floorTextures = new ArrayList<>();

	public WaterBlock() {
		isOpaque = false;
		isSolid = true;
		isStatic = true;

		floorTex = Texture.water0;
		ceilTex = Texture.floor;

		floorTextures.add(Texture.water0);
		floorTextures.add(Texture.water1);
		floorTextures.add(Texture.water2);
	}

    public void tick() {
        super.tick();
        texTick();
    }

    private void texTick() {
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
}