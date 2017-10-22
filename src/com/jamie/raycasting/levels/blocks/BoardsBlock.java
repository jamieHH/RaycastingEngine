package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.entities.particles.WoodParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BoardsBlock extends Block
{
	private boolean smashed = false;

	public BoardsBlock() {
        solidRender = false;
        blocksMotion = true;

        Sprite sprite = new Sprite(Texture.boards);
        addSprite(sprite);
	}

	public boolean use() {
        if (!smashed) {
            trigger();
            return true;
        }
        return false;
    }

    public void trigger() {
        smashed = true;
        blocksMotion = false;

        clearSprites();

        Sprite sprite = new Sprite(Texture.boardsSmashed);
        addSprite(sprite);

        for (int i = 0; i < 6 ; i++) {
            level.addEntity(new WoodParticle((gridX * 16) + 8, (gridZ * 16) + 8));
        }
    }
}
