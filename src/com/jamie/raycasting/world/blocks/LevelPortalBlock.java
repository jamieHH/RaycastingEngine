package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.Entity;

import java.util.List;

public class LevelPortalBlock extends FunctionBlock
{
    public boolean disabled = false;

	public LevelPortalBlock() {
		isOpaque = false;
		isSolid = false;
	}

	public void tick() {
        List<Entity> ents = level.getEntitiesWithin(gridX, gridZ, gridX + 1, gridZ + 1);

        if (disabled) {
            if (ents.size() == 0) {
                disabled = false;
            }
        } else {
            for (int i = 0; i < ents.size(); i++) {
                level.switchLevel(ents.get(i), id);
            }
        }
    }
}
