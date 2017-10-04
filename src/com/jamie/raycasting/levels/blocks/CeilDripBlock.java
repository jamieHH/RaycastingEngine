package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class CeilDripBlock extends Block
{
//    public int animTime = 5;
//    public int spriteIndex = 0;

	public CeilDripBlock() {
        blocksMotion = false;

        floorTex = Texture.wetFloor;
        ceilTex = Texture.wetFloor;

		Render[] ts = {
                Texture.drip0,
                Texture.drip0,
                Texture.drip1,
                Texture.drip1,
                Texture.drip2,
                Texture.drip3,
                Texture.drip4,
                Texture.drip0,
		};

        Sprite sprite = new Sprite(ts);
        addSprite(sprite);
	}

	public void tick() {
	    // TODO: why does standing code to the sprite kill the cpu? render issue??
        super.tick();
	    texTick();
    }

    public void texTick() {
	    // TODO: generify this to apply to all block types
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
