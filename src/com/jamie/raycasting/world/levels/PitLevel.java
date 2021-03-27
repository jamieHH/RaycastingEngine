package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.MiscItem;
import com.jamie.raycasting.world.blocks.GateBlock;

public class PitLevel extends Level
{
	public PitLevel() {
        name = "Pit";
	}

    protected Bitmap getFloorTexture() {
        return Texture.dirt;
    }

    protected Bitmap getCeilingTexture() {
        return Texture.dirt;
    }

    protected Bitmap getWallTexture() {
        return Texture.wall2;
    }

	protected void postCreate() {
        addEntity(new Drop(new MiscItem("Bronze Key", Texture.keyIcon)), 27.5, 26.5);
        setBlock(25, 39, new GateBlock(getFloorTexture(), getCeilingTexture(), "Bronze Key"));

        addEntity(new Drop(new MiscItem("Planks", Texture.planksIcon)), 12.5, 29.5);
    }

    public void switchLevel(Entity entity, String ref) {
        if (ref.equals("2524")) world.switchLevel(entity, "island", "3611");
    }

    public void triggerBlock(String ref) {
        if (ref.equals("2116")) super.triggerBlock("2114");
    }
}
