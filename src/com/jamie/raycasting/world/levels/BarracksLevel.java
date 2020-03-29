package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.AreaAlertEntity;
import com.jamie.raycasting.entities.BouncingEntity;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.weapons.AxeWeapon;
import com.jamie.raycasting.items.weapons.SpearWeapon;

public class BarracksLevel extends Level
{
	public BarracksLevel() {
		name = "Barracks";
	}

	protected void postCreate() {
		Drop d0 = new Drop(new AxeWeapon());
		addEntity(d0, 6.5, 19.5);
		AreaAlertEntity a0 = new AreaAlertEntity("This Axe can be used to break down wooden boards.");
		addEntity(a0, 6.5, 19.5);

		Drop s0 = new Drop(new SpearWeapon());
		addEntity(s0, 14.5, 21.5);
		AreaAlertEntity a1 = new AreaAlertEntity("Some weapons have a longer striking distances.");
		addEntity(a1, 14.5, 21.5);


		Bitmap[] ts0 = {
				Texture.spinningDummy0,
				Texture.spinningDummy1,
				Texture.spinningDummy2,
				Texture.spinningDummy3,
				Texture.spinningDummy4,
		};
		addEntity(new BouncingEntity(new Sprite(ts0), "W", 0.1, 1),13.5, 11.5);
		addEntity(new BouncingEntity(new Sprite(ts0), "W", 0.1, 1),15.5, 14.5);
		addEntity(new BouncingEntity(new Sprite(ts0), "W", 0.1, 1),11.5, 17.5);
	}

	public void switchLevel(Entity entity, int id) {
		if (id == 1) world.switchLevel(entity, "castleHall", 3);
	}

    public void triggerBlock(int id) {
        if (id == 1) super.triggerBlock(2);
    }
}
