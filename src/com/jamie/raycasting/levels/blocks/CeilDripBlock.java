package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class CeilDripBlock extends Block {

//    public int animTime = 5;
//    public int spriteIndex = 0;

	public CeilDripBlock() {
		Sprite sprite1 = new Sprite(0, 0, 0, Texture.drip0);
		Sprite sprite2 = new Sprite(0, 0, 0, Texture.drip1);
		Sprite sprite3 = new Sprite(0, 0, 0, Texture.drip2);
		Sprite sprite4 = new Sprite(0, 0, 0, Texture.drip3);
		Sprite sprite5 = new Sprite(0, 0, 0, Texture.drip4);

		addSprite(sprite1);
		addSprite(sprite1);
		addSprite(sprite2);
        addSprite(sprite2);
		addSprite(sprite3);
		addSprite(sprite4);
		addSprite(sprite5);
        addSprite(sprite1);

		blocksMotion = false;

		floorTex = Texture.wetFloor;
		ceilTex = Texture.wetFloor;
	}

	public void tick() {
	    // TODO: why does standing code to the sprite kill the cpu? render issue??
	    texTick();
    }

    public void texTick() {
        if (animTime > 0) {
            animTime--;
            return;
        }

        int i = spriteIndex;
        i++;
        if (i >= countSprites()) {
            i = 0;
        }

        spriteIndex = i;
        animTime = 5;
    }
}
