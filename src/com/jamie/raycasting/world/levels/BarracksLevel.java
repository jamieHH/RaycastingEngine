package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.AreaAlertEntity;
import com.jamie.raycasting.entities.BouncingEntity;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Imp;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.MiscItem;
import com.jamie.raycasting.items.consumables.HealthPotion;
import com.jamie.raycasting.items.weapons.AxeWeapon;
import com.jamie.raycasting.items.weapons.SpearWeapon;
import com.jamie.raycasting.world.blocks.DoorBlock;
import com.jamie.raycasting.world.blocks.GateBlock;
import com.jamie.raycasting.world.blocks.PressurePlateBlock;

public class BarracksLevel extends Level
{
	public BarracksLevel() {
		name = "Barracks";
	}

	protected Bitmap getWallTexture() {
		return Texture.wall4;
	}

	protected void postCreate() {
		Drop d0 = new Drop(new AxeWeapon());
		addEntity(d0, 6.5, 35.5);
		AreaAlertEntity a0 = new AreaAlertEntity("This Axe can be used to break down wooden boards.");
		addEntity(a0, 6.5, 35.5);

		Drop s0 = new Drop(new SpearWeapon());
		addEntity(s0, 14.5, 21.5);
		AreaAlertEntity a1 = new AreaAlertEntity("Some weapons have longer striking distances.");
		addEntity(a1, 14.5, 21.5);

		Drop armoryKey = new Drop(new MiscItem("Armory Key", Texture.keyIcon));
		addEntity(new Imp(armoryKey), 1.5, 32.5);

		addEntity(new Drop(new HealthPotion()), 6.5, 19.5);

		setBlock(8, 33, new GateBlock(getFloorTexture(), getCeilingTexture(), "Armory Key"));

		PressurePlateBlock b = new PressurePlateBlock(getCeilingTexture());
		b.setReference("pressurePad");
		setBlock(7, 23, b);

		DoorBlock db = new DoorBlock(getFloorTexture(), getCeilingTexture(), false);
		db.setReference("door");
		setBlock(8	,23, db);


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
        if (id == 1) super.triggerBlock(3);

    }

	public void triggerBlock(String reference) {
		if (reference.equals("pressurePad")) {
			super.setBlockState("door", false);
		}
	}
}
