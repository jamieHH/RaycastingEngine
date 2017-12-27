package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class LadderBlock extends Block
{
    public boolean disabled = false;

	public LadderBlock(boolean ladderUp) {
		isOpaque = false;
		isSolid = false;

		if  (ladderUp) {
			floorTex = Texture.floor;
			ceilTex = Texture.ladderHole;

            Sprite sprite = new Sprite(Texture.ladder, 0, 0.125, 0);
            addSprite(sprite);
		} else {
            floorTex = Texture.ladderHole;
            ceilTex = Texture.floor;

            Sprite sprite = new Sprite(Texture.ladder, 0, -0.125, 0);
            addSprite(sprite);
        }
	}

	public void tick() {
        if (level.player != null) { // stopgap for null player exception
            if (disabled) {
                if (level.player.squareDistanceFrom(gridX + 0.5, gridZ + 0.5) > 1) {
                    disabled = false;
                }
            } else {
                if (level.player.squareDistanceFrom(gridX + 0.5, gridZ + 0.5) < 1) {
                    level.switchLevel(id);
                }
            }
        }
    }
}
