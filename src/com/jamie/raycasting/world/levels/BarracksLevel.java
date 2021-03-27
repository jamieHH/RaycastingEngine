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

		addEntity(new Drop(new HealthPotion()), 6.5, 19.5);
		addEntity(new Drop(new HealthPotion()), 19.5, 4.5);

		setBlock(8, 33, new GateBlock(getFloorTexture(), getCeilingTexture(), "Barracks Stash Key"));
		Drop armoryKey = new Drop(new MiscItem("Barracks Stash Key", Texture.keyIcon));
		addEntity(new Imp(armoryKey), 1.5, 32.5);

		setBlock(17, 4, new GateBlock(getFloorTexture(), getCeilingTexture(), "Armory Key"));

		PressurePlateBlock b = new PressurePlateBlock(getCeilingTexture());
		b.setReference("pressurePad");
		setBlock(7, 23, b);

		DoorBlock db = new DoorBlock(getFloorTexture(), getCeilingTexture(), false);
		db.setReference("door");
		setBlock(8	,23, db);
	}

	public void switchLevel(Entity entity, String ref) {
		if (ref.equals("0302")) world.switchLevel(entity, "castleHall", "1306");
		if (ref.equals("0718")) world.switchLevel(entity, "armory", "1225");
		if (ref.equals("2426")) world.switchLevel(entity, "armory", "3414");
	}

	public void triggerBlock(String ref) {
		if (ref.equals("0007")) super.triggerBlock("0707");
		if (ref.equals("pressurePad")) super.setBlockState("door", false);
	}
}
