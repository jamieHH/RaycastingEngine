package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.AreaAlertEntity;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.items.weapons.AxeWeapon;

public class BarracksLevel extends Level
{
	public BarracksLevel() {
		name = "Barracks";
	}

	protected void postCreate() {
		Drop d0 = new Drop(new AxeWeapon());
		addEntity(d0, 6.5, 17.5);

		AreaAlertEntity a0 = new AreaAlertEntity("This Axe can be used to break down wooden boards.");
		addEntity(a0, 6.5, 17.5);
	}

	public void switchLevel(Mob mob, int id) {
		if (id == 1) world.switchLevel(mob, "castleHall", 3);
	}

    public void triggerBlock(int id) {
        if (id == 1) super.triggerBlock(2);
    }
}
