package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

import java.util.List;

public class LadderBlock extends Block
{
    public boolean disabled = false;

	public LadderBlock(boolean ladderUp) {
		isOpaque = false;
		isSolid = false;

		if  (ladderUp) {
			floorTex = Texture.floor;
			ceilTex = Texture.ladderHole;

            addIdleSprite(new Sprite(Texture.ladder, 0, 0.125, 0));
		} else {
            floorTex = Texture.ladderHole;
            ceilTex = Texture.floor;

            addIdleSprite(new Sprite(Texture.ladder, 0, -0.125, 0));
        }
	}

	public void tick() {
        List<Mob> mobs = level.getMobsWithin(gridX, gridZ, gridX + 1, gridZ + 1);

        if (disabled) {
            if (mobs.size() == 0) {
                disabled = false;
            }
        } else {
            for (int i = 0; i < mobs.size(); i++) {
                level.switchLevel(id);
            }
        }
    }
}
