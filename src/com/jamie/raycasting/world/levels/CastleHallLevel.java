package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.items.consumables.HealthPotion;

public class CastleHallLevel extends Level
{
	public CastleHallLevel() {
	    name = "CastleHall";
	}

    protected void postCreate() {
        Drop d0 = new Drop(new HealthPotion());
        addEntity(d0, 10.5, 6.5);
    }

    public void switchLevel(Mob mob, int id) {
        if (id == 2) world.switchLevel(mob, "island", 2);
        if (id == 3) world.switchLevel(mob, "barracks", 1);
    }
}
