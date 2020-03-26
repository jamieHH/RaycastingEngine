package com.jamie.raycasting.world.levels;

import com.jamie.raycasting.entities.AreaAlertEntity;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.items.consumables.HealthPotion;
import com.jamie.raycasting.items.weapons.KnifeWeapon;

public class PrisonLevel extends Level
{
	public PrisonLevel() {
        name = "Prison";
	}

	protected void postCreate() {
	    Drop d0 = new Drop(new HealthPotion());
        addEntity(d0, 16.5, 20.5);

        Drop d1 = new Drop(new KnifeWeapon());
        addEntity(d1, 13.5, 30.5);

        AreaAlertEntity a0 = new AreaAlertEntity("Press [E] to open your Inventory");
        addEntity(a0, 16.5, 20.5);
    }

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "sewer", 1);
        if (id == 2) world.switchLevel(mob, "dungeon", 1);
    }

    public void triggerBlock(int id) {
        if (id == 1) super.triggerBlock(2);
    }
}
