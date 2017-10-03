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

        Sprite sprite = new Sprite(Texture.boards, 0, 0, 0);
        addSprite(sprite);
	}

	public boolean use() {
        if (!smashed) {
            smash();
            return true;
        }
        return false;
    }

    private void smash() {
        smashed = true;
        blocksMotion = false;

        clearSprites();

        Sprite sprite = new Sprite(Texture.boardsSmashed, 0, 0, 0);
        addSprite(sprite);

        for (int i = 0; i < 6 ; i++) {
            level.addEntity(new WoodParticle((gridX * 16) + 8, (gridZ * 16) + 8));
        }
    }
}
