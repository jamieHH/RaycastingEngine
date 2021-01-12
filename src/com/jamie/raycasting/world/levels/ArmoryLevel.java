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

public class ArmoryLevel extends Level
{
	public ArmoryLevel() {
		name = "Armory";
	}

	protected Bitmap getWallTexture() {
		return Texture.wall4;
	}

	protected void postCreate() {
		addEntity(new Drop(new SpearWeapon()), 32.5, 12.5);
		addEntity(new AreaAlertEntity("Some weapons have longer striking distances."), 32.5, 12.5);

		addEntity(new Drop(new HealthPotion()), 21.5, 28.5);

		addEntity(new Drop(new HealthPotion()), 27.5, 20.5);

		addEntity(new Drop(new HealthPotion()), 39.5, 13.5);

		addEntity(new Drop(new HealthPotion()), 19.5, 13.5);

		addEntity(new Drop(new MiscItem("Armory Key", Texture.keyIcon)), 28.5, 36.5);

		setBlock(32, 14, new GateBlock(getFloorTexture(), getCeilingTexture(), "Armory Key"));
//
//		PressurePlateBlock b = new PressurePlateBlock(getCeilingTexture());
//		b.setReference("pressurePad");
//		setBlock(7, 23, b);
//
//		DoorBlock db = new DoorBlock(getFloorTexture(), getCeilingTexture(), false);
//		db.setReference("door");
//		setBlock(8	,23, db);


		Bitmap[] ts0 = {
				Texture.spinningDummy0,
				Texture.spinningDummy1,
				Texture.spinningDummy2,
				Texture.spinningDummy3,
				Texture.spinningDummy4,
		};

		addEntity(new BouncingEntity(new Sprite(ts0), "W", 0.1, 1),14.5, 24.5);
		addEntity(new BouncingEntity(new Sprite(ts0), "W", 0.1, 1),16.5, 27.5);
		addEntity(new BouncingEntity(new Sprite(ts0), "W", 0.1, 1),14.5, 30.5);

		addEntity(new BouncingEntity(new Sprite(ts0), "W", 0.1, 1),24.5, 27.5);

		addEntity(new BouncingEntity(new Sprite(ts0), "S", 0.1, 1),21.5, 23.5);
		addEntity(new BouncingEntity(new Sprite(ts0), "S", 0.1, 1),26.5, 24.5);
		addEntity(new BouncingEntity(new Sprite(ts0), "S", 0.1, 1),28.5, 25.5);
	}

	public void switchLevel(Entity entity, int id) {
		if (id == 1) world.switchLevel(entity, "barracks", 2);
		if (id == 2) world.switchLevel(entity, "barracks", 3);
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
