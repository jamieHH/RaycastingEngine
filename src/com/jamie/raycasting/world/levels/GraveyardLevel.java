package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.AreaAlertEntity;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.MiscItem;
import com.jamie.raycasting.world.blocks.StrongDoorBlock;

public class GraveyardLevel extends Level
{
	public GraveyardLevel() {
		name = "Graveyard";
		height = 64;
        fogColor = 0x000010;
		isOutside = true;
	}

	protected Bitmap getFloorTexture() {
		return Texture.grass;
	}

	protected Bitmap getCeilingTexture() {
		return null;
	}

	protected void postCreate() {
		Drop d0 = new Drop(new MiscItem("Grey Key", Texture.keyIcon));
		addEntity(d0, 14.5, 51.5);

		Drop d1 = new Drop(new MiscItem("Planks", Texture.planksIcon));
		addEntity(d1, 11.5, 32.5);

		AreaAlertEntity a0 = new AreaAlertEntity("I can use these Planks to repair the bridge.");
		addEntity(a0, 14.5, 44.5);


		StrongDoorBlock md0 = new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), false);
		md0.setReference("mazeDoor");
		setBlock(9, 47, md0);


		StrongDoorBlock sd0 = new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), false);
		StrongDoorBlock sd0o = new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), true);
		sd0.setReference("l1Doors");
		sd0o.setReference("l1Doors");
		setBlock(8, 43, sd0);
		setBlock(12, 43, sd0o);

		StrongDoorBlock sd1 = new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), false);
		StrongDoorBlock sd1o = new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), true);
		sd1.setReference("l2Doors");
		sd1o.setReference("l2Doors");
		setBlock(7, 38, sd1);
		setBlock(11, 38, sd1o);

		StrongDoorBlock sd2 = new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), false);
		StrongDoorBlock sd2o = new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), true);
		sd2.setReference("l3Doors");
		sd2o.setReference("l3Doors");
		setBlock(7, 30, sd2);
		setBlock(7, 34, sd2o);
	}

    public void switchLevel(Entity entity, String ref) {
        if (ref.equals("6007")) world.switchLevel(entity, "island", "1643");
    }

	public void triggerBlock(String ref) {
		if (ref.equals("3207")) super.triggerBlock("l3Doors");
		if (ref.equals("3709")) super.triggerBlock("l2Doors");
		if (ref.equals("4310")) super.triggerBlock("l1Doors");
		if (ref.equals("4914")) super.triggerBlock("mazeDoor");
	}
}
