package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.weapons.XbowWeapon;
import com.jamie.raycasting.world.blocks.DoorBlock;
import com.jamie.raycasting.world.blocks.PressurePlateBlock;

public class CryptLevel extends Level
{
	public CryptLevel() {
	    name = "Crypt";
	}

    protected Bitmap getFloorTexture() {
        return Texture.floor1;
    }

    protected Bitmap getCeilingTexture() {
        return Texture.floor1;
    }

    protected Bitmap getWallTexture() {
        return Texture.wall5;
    }

    protected void postCreate() {
        addEntity(new Drop(new XbowWeapon()), 28.5, 23.5);

        PressurePlateBlock b = new PressurePlateBlock(getCeilingTexture());
        b.setReference("pressurePad");
        setBlock(28, 21, b);

        DoorBlock db = new DoorBlock(getFloorTexture(), getCeilingTexture(), false);
        db.setReference("door");
        setBlock(28,22, db);
    }

    public void switchLevel(Entity entity, String ref) {
        if (ref.equals("3312")) world.switchLevel(entity, "island", "2248");
    }

    public void triggerBlock(String ref) {
        if (ref.equals("0812")) super.triggerBlock("1216");
        if (ref.equals("pressurePad")) super.setBlockState("door", false);
    }
}
