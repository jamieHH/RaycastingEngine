package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.entities.WoodParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BoardsBlock extends Block {
	public boolean smashed = false;

	public BoardsBlock() {
		solidRender = false;
		blocksMotion = true;

        Sprite sprite = new Sprite(0, 0, 0, Texture.boards);
        addSprite(sprite);
	}

	public boolean use() {
        if (!smashed) {
            smash();
            return true;
        }
        return false;
    }

    public void smash() {
        smashed = true;
        blocksMotion = false;

        clearSprites();

        Sprite sprite = new Sprite(0, 0, 0, Texture.boardsSmashed);
        addSprite(sprite);

        for (int i = 0; i < 6 ; i++) {
            level.addEntity(new WoodParticle((gridX * 16) + 8, (gridZ * 16) + 8));
        }
    }
}
