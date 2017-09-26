package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class LadderBlock extends Block
{
    public boolean disabled = false;

	public LadderBlock(boolean ladderUp) {
		solidRender = false;
		blocksMotion = false;

		if  (ladderUp) {
			floorTex = Texture.floor;
			ceilTex = Texture.ladderHole;

            Sprite sprite = new Sprite(0, 0.2, 0, Texture.ladder);
            addSprite(sprite);
		} else {
            floorTex = Texture.ladderHole;
            ceilTex = Texture.floor;

            Sprite sprite = new Sprite(0, -0.15, 0, Texture.ladder);
            addSprite(sprite);
        }
	}

	public void tick() {
        if (level.player != null) { // stopgap for null player exception
            if (disabled) {
                if (level.player.squareDistanceFrom((gridX * 16) + 8, (gridZ * 16) + 8) > 16) {
                    disabled = false;
                }
            } else {
                if (level.player.squareDistanceFrom((gridX * 16) + 8, (gridZ * 16) + 8) < 16) {
                    level.switchLevel(id);
                }
            }
        }
    }
}
