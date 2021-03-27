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
import com.jamie.raycasting.world.LogicTimer;
import com.jamie.raycasting.world.blocks.DoorBlock;
import com.jamie.raycasting.world.blocks.GateBlock;
import com.jamie.raycasting.world.blocks.PressurePlateBlock;
import com.jamie.raycasting.world.blocks.TurretBlock;

public class ArmoryLevel extends Level
{
	public ArmoryLevel() {
		name = "Armory";
	}

	protected Bitmap getWallTexture() {
		return Texture.wall4;
	}

	protected void postCreate() {
		addEntity(new Drop(new HealthPotion()), 21.5, 28.5);
		addEntity(new Drop(new HealthPotion()), 27.5, 20.5);
		addEntity(new Drop(new HealthPotion()), 39.5, 13.5);
		addEntity(new Drop(new HealthPotion()), 19.5, 13.5);

		setBlock(32, 14, new GateBlock(getFloorTexture(), getCeilingTexture(), "Armory Key"));
		addEntity(new Drop(new MiscItem("Armory Key", Texture.keyIcon)), 28.5, 36.5);

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

		addEntity(new Drop(new SpearWeapon()), 32.5, 12.5);
		addEntity(new AreaAlertEntity("Some weapons have longer striking distances."), 32.5, 12.5);


		TurretBlock tb0 = new TurretBlock("N");
		tb0.setReference("turret0");
		setBlock(35, 27, tb0);
		TurretBlock tb1 = new TurretBlock("N");
		tb1.setReference("turret1");
		setBlock(35, 24, tb1);
//		TurretBlock tb2 = new TurretBlock("N");
//		tb2.setReference("turret2");
//		setBlock(35, 21, tb2);

		PressurePlateBlock pp0 = new PressurePlateBlock(getCeilingTexture());
		pp0.setReference("pp0");
		setBlock(31, 27, pp0); // TODO: solve multiple plates doubling firerate!?!
		PressurePlateBlock pp1 = new PressurePlateBlock(getCeilingTexture());
		pp1.setReference("pp1");
		setBlock(32, 24, pp1);
//		PressurePlateBlock pp2 = new PressurePlateBlock(getCeilingTexture());
//		pp2.setReference("pp2");
//		setBlock(33, 21, pp2);
	}

	public void switchLevel(Entity entity, String id) {
		if (id.equals("3414")) world.switchLevel(entity, "barracks", "2426");
		if (id.equals("1225")) world.switchLevel(entity, "barracks", "0718");
	}

	public void triggerBlock(String reference) {
		if (reference.equals("pp0")) {
			super.triggerBlock("turret0");
		}
		if (reference.equals("pp1")) {
			super.triggerBlock("turret1");
		}
//		if (reference.equals("pp2")) {
//			super.triggerBlock("turret2");
//		}
	}
}
