package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.mobs.Mob;

import java.util.List;

public class LevelPortalBlock extends FunctionBlock
{
    public boolean disabled = false;

	public LevelPortalBlock() {
		isOpaque = false;
		isSolid = false;
	}

	public void tick() {
        List<Mob> mobs = level.getMobsWithin(gridX, gridZ, gridX + 1, gridZ + 1);

        if (disabled) {
            if (mobs.size() == 0) {
                disabled = false;
            }
        } else {
            for (int i = 0; i < mobs.size(); i++) {
                level.switchLevel(mobs.get(i), id);
            }
        }
    }
}
