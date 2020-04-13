package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;

public class WaterBlock extends Block
{
    private int animTime = 20;
    private int floorTexIndex = 0;

    private ArrayList<Bitmap> floorTextures = new ArrayList<>();

	public WaterBlock(Bitmap ceilTex) {
		isOpaque = false;
		isSolid = false;
		isWalkable = false;

		floorTex = Texture.water0;
		this.ceilTex = ceilTex;

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

        floorTexIndex++;
        if (floorTexIndex >= floorTextures.size()) {
            floorTexIndex = 0;
        }

        animTime = 20;
        floorTex = floorTextures.get(floorTexIndex);
    }
}
